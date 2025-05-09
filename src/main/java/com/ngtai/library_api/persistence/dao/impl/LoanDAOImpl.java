package com.ngtai.library_api.persistence.dao.impl;

import com.ngtai.library_api.persistence.ConnectionUtil;
import com.ngtai.library_api.persistence.dao.LoanDAO;
import com.ngtai.library_api.persistence.entity.LoanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

@Repository
public class LoanDAOImpl implements LoanDAO {

    private final ConnectionUtil connectionUtil;

    @Autowired
    public LoanDAOImpl(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public void insert(final LoanEntity entity) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement
                        ("INSERT INTO loan (book_id, reader_id) VALUES (?, ?, ?, ?)",
                                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, entity.getBookId());
            statement.setLong(2, entity.getReaderId());
            statement.executeUpdate();
            try (var rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(final LoanEntity entity) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement
                        ("UPDATE loan SET return_date = ?, book_id = ?, reader_id = ? WHERE id = ?")) {
            if (entity.getReturnDate() != null) {
                statement.setTimestamp(1,
                        Timestamp.from(entity.getReturnDate().toInstant()));
            } else {
                statement.setNull(1, Types.TIMESTAMP);
            }
            statement.setLong(2, entity.getBookId());
            statement.setLong(3, entity.getReaderId());
            statement.setLong(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(final Long id) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement("DELETE FROM loan WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.printf("%d records deleted.\n", statement.getUpdateCount());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<LoanEntity> findAll() {
        List<LoanEntity> loanList = new ArrayList<>();
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement("SELECT * FROM loan ORDER BY loan_date ASC");
                var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                var entity = new LoanEntity();
                entity.setId(resultSet.getLong("id"));

                var loanDateTs = resultSet.getTimestamp("created_at");
                if (loanDateTs != null) {
                    entity.setCreatedAt(OffsetDateTime.ofInstant(loanDateTs.toInstant(), UTC));
                }

                var returnDateTs = resultSet.getTimestamp("return_date");
                if (returnDateTs != null) {
                    entity.setReturnDate(OffsetDateTime.ofInstant(returnDateTs.toInstant(), UTC));
                }

                entity.setBookId(resultSet.getLong("book_id"));
                entity.setReaderId(resultSet.getLong("reader_id"));

                loanList.add(entity);
            }
//            System.out.printf("%d records found.\n", statement.getUpdateCount());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return loanList;
    }

    public LoanEntity findById(final Long id) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement("SELECT * FROM loan WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            if (resultSet.next()) {
                var entity = new LoanEntity();

                entity.setId(resultSet.getLong("id"));

                var loanDate = resultSet.getTimestamp("created_at");
                if (loanDate != null) {
                    entity.setCreatedAt(OffsetDateTime.ofInstant(loanDate.toInstant(), UTC));
                }

                var returnDate = resultSet.getTimestamp("return_date");
                if (returnDate != null) {
                    entity.setReturnDate(OffsetDateTime.ofInstant(returnDate.toInstant(), UTC));
                }

                entity.setBookId(resultSet.getLong("book_id"));
                entity.setReaderId(resultSet.getLong("reader_id"));
                return entity;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteAll() {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement("DELETE FROM loan")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}