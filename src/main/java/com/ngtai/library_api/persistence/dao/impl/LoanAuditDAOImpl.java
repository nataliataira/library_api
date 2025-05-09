package com.ngtai.library_api.persistence.dao.impl;

import com.ngtai.library_api.persistence.ConnectionUtil;
import com.ngtai.library_api.persistence.dao.LoanAuditDAO;
import com.ngtai.library_api.persistence.entity.LoanAuditEntity;
import com.ngtai.library_api.persistence.entity.enums.OperationEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

@Repository
public class LoanAuditDAOImpl implements LoanAuditDAO {

    private final ConnectionUtil connectionUtil;

    @Autowired
    public LoanAuditDAOImpl(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public List<LoanAuditEntity> findAll() {
        List<LoanAuditEntity> entities = new ArrayList<>();
        try (
                var connection = connectionUtil.getConnection();
                var statement = connection.prepareStatement("SELECT * FROM view_loan_audit")) {
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            while (resultSet.next()) {
                entities.add(new LoanAuditEntity(
                        resultSet.getLong("loan_id"),
                        resultSet.getLong("reader_id"),
                        resultSet.getLong("older_reader_id"),
                        resultSet.getLong("book_id"),
                        resultSet.getLong("old_book_id"),
                        getDateTimeOrNull(resultSet, "return_date"),
                        getDateTimeOrNull(resultSet, "old_return_date"),
                        getDateTimeOrNull(resultSet, "created_at"),
                        OperationEnum.getByDbOperation(resultSet.getString("operation"))
                ));
            }
//            System.out.printf("%d records found.\n", statement.getUpdateCount());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entities;
    }

    public OffsetDateTime getDateTimeOrNull(final ResultSet resultSet, final String field) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(field);
        return timestamp == null ? null : OffsetDateTime.ofInstant(timestamp.toInstant(), UTC);
    }
}