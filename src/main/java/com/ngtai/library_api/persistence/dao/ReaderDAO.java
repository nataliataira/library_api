package com.ngtai.library_api.persistence.dao;

import com.ngtai.library_api.persistence.entity.ReaderEntity;

import java.util.List;

public interface ReaderDAO {
    public void insert(final ReaderEntity entity);
    public void update(final ReaderEntity entity);
    public void delete(final Long id);
    public List<ReaderEntity> findAll();
    public ReaderEntity findById(final Long id);
}
