package com.ngtai.library_api.persistence.dao.impl;

import com.ngtai.library_api.persistence.ConnectionUtil;
import com.ngtai.library_api.persistence.dao.ReaderDAO;
import com.ngtai.library_api.persistence.entity.ReaderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderDAOImpl implements ReaderDAO {

    private final ConnectionUtil connectionUtil;

    @Autowired
    public ReaderDAOImpl(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public void insert(final ReaderEntity entity) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement
                        ("INSERT INTO reader (name, email) VALUES (?, ?)",
                                java.sql.Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
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

    public void update(final ReaderEntity entity) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement
                        ("UPDATE reader SET name = ?, email = ? WHERE id = ?")) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setLong(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(final Long id) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement("DELETE FROM reader WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.printf("%d records deleted.\n", statement.getUpdateCount());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<ReaderEntity> findAll() {
        List<ReaderEntity> readerList = new ArrayList<>();
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement("SELECT * FROM reader ORDER BY name ASC")) {
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            while (resultSet.next()) {
                var entity = new ReaderEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setEmail(resultSet.getString("email"));
                readerList.add(entity);
            }
//            System.out.printf("%d records found.\n", statement.getUpdateCount());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return readerList;
    }

    public ReaderEntity findById(final Long id) {
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement("SELECT * FROM reader WHERE id = ?")) {
            statement.setLong(1, id);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    var entity = new ReaderEntity();
                    entity.setId(resultSet.getLong("id"));
                    entity.setName(resultSet.getString("name"));
                    entity.setEmail(resultSet.getString("email"));
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
                var statement = connection.prepareStatement("DELETE FROM reader")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}