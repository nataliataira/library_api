package com.ngtai.library_api.service;

import com.ngtai.library_api.persistence.dao.ReaderDAO;
import com.ngtai.library_api.persistence.entity.ReaderEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {

    private final ReaderDAO repository;

    public ReaderService(ReaderDAO repository) {
        this.repository = repository;
    }

    public void createReader(ReaderEntity entity) {
        repository.insert(entity);
    }

    public ReaderEntity findReaderById(Long id) {
        ReaderEntity entity = repository.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("This reader does not exist.");
        }
        return entity;
    }

    public List<ReaderEntity> findAllReaders() {
        List<ReaderEntity> readers = repository.findAll();
        if (readers == null) {
            throw new IllegalArgumentException("No readers found.");
        }
        return readers;
    }

    public void updateReader(ReaderEntity entity) {
        ReaderEntity oldEntity = repository.findById(entity.getId());
        if (oldEntity == null) {
            throw new IllegalArgumentException("This reader does not exist.");
        }
        repository.update(entity);
    }

    public void deleteReader(Long id) {
        ReaderEntity entity = repository.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("This reader does not exist.");
        }
        repository.delete(id);
    }
}
