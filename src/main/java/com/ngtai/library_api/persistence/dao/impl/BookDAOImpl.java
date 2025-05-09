package com.ngtai.library_api.persistence.dao.impl;

import com.ngtai.library_api.persistence.ConnectionUtil;
import com.ngtai.library_api.persistence.dao.BookDAO;
import com.ngtai.library_api.persistence.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private final ConnectionUtil connectionUtil;

    @Autowired
    public BookDAOImpl(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public BookEntity insert(final BookEntity entity) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement
                        ("INSERT INTO book (title, author, loaned) VALUES (?, ?, ?)",
                                java.sql.Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getAuthor());
            if (entity.getLoaned() == null) {
                statement.setNull(3, Types.BOOLEAN);
            } else {
                statement.setBoolean(3, entity.getLoaned());
            }
            statement.executeUpdate();
            try (var resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    entity.setId(resultSet.getLong(1));
                    return entity;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void update(final BookEntity entity) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement
                        ("UPDATE book SET title = ?, author = ?, loaned = ? WHERE id = ?")) {
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getAuthor());
            if (entity.getLoaned() == null) {
                statement.setNull(3, Types.BOOLEAN);
            } else {
                statement.setBoolean(3, entity.getLoaned());
            }
            statement.setLong(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(final Long id) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement("DELETE FROM book WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.printf("%d records deleted.\n", statement.getUpdateCount());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<BookEntity> findAll() {
        List<BookEntity> bookList = new ArrayList<>();
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement("SELECT * FROM book ORDER BY title ASC")) {
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            while (resultSet.next()) {
                var entity = new BookEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setTitle(resultSet.getString("title"));
                entity.setAuthor(resultSet.getString("author"));
                entity.setLoaned(resultSet.getBoolean("loaned"));
                bookList.add(entity);
            }
//            System.out.printf("%d records found.\n", statement.getUpdateCount());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookList;
    }

    public BookEntity findById(final Long id) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement("SELECT * FROM book WHERE id = ?")) {
            statement.setLong(1, id);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    var entity = new BookEntity();
                    entity.setId(resultSet.getLong("id"));
                    entity.setTitle(resultSet.getString("title"));
                    entity.setAuthor(resultSet.getString("author"));
                    entity.setLoaned(resultSet.getBoolean("loaned"));
                    return entity;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public BookEntity findByTitle(final String title) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement("SELECT * FROM book WHERE title = ?")) {
            statement.setString(1, title);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    var entity = new BookEntity();
                    entity.setId(resultSet.getLong("id"));
                    entity.setTitle(resultSet.getString("title"));
                    entity.setAuthor(resultSet.getString("author"));
                    entity.setLoaned(resultSet.getBoolean("loaned"));
                    return entity;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteAll() {
        try (
             var connection = connectionUtil.getConnection();
             var statement = connection.prepareStatement("DELETE FROM book")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}