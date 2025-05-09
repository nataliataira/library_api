package com.ngtai.library_api.persistence.dao;

import com.ngtai.library_api.persistence.entity.LoanEntity;

import java.util.List;


public interface LoanDAO {
    public void insert(final LoanEntity entity);
    public void update(final LoanEntity entity);
    public void delete(final Long id);
    public List<LoanEntity> findAll();
    public LoanEntity findById(final Long id);
}
