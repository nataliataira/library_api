package com.ngtai.library_api.persistence.dao;

import com.ngtai.library_api.persistence.entity.LoanAuditEntity;

import java.util.List;

public interface LoanAuditDAO {
    public List<LoanAuditEntity> findAll();
}
