package com.ngtai.library_api.persistence.dao;

import com.ngtai.library_api.persistence.entity.BookEntity;

import java.util.List;

public interface BookDAO {
    public BookEntity insert(final BookEntity entity);
    public void update(final BookEntity entity);
    public void delete(final Long id);
    public List<BookEntity> findAll();
    public BookEntity findById(final Long id);
    public BookEntity findByTitle(final String title);
}
