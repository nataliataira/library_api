package com.ngtai.library_api.service;

import com.ngtai.library_api.persistence.dao.LoanAuditDAO;
import com.ngtai.library_api.persistence.entity.LoanAuditEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanAuditService {

    private final LoanAuditDAO repository;

    public LoanAuditService(LoanAuditDAO loanAuditDAO) {
        this.repository = loanAuditDAO;
    }

    public List<LoanAuditEntity> findAllAuditLoans() {
        List<LoanAuditEntity> allData = repository.findAll();
        if (allData.isEmpty()) {
            throw new IllegalArgumentException("No loans found.");
        }
        return allData;
    }
}