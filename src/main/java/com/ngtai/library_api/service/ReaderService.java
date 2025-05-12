package com.ngtai.library_api.service;

import com.ngtai.library_api.exception.ReaderNotFoundException;
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
            throw new ReaderNotFoundException(id);
        }
        return entity;
    }

    public List<ReaderEntity> findAllReaders() {
        return repository.findAll();
    }

    public void updateReader(ReaderEntity entity) {
        ReaderEntity oldEntity = repository.findById(entity.getId());
        if (oldEntity == null) {
            throw new ReaderNotFoundException(entity.getId());
        }
        repository.update(entity);
    }

    public void deleteReader(Long id) {
        ReaderEntity entity = repository.findById(id);
        if (entity == null) {
            throw new ReaderNotFoundException(id);
        }
        repository.delete(id);
    }
}
