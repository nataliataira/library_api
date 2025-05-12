package com.ngtai.library_api.service;

import com.ngtai.library_api.exception.BookAlreadyExistsException;
import com.ngtai.library_api.exception.BookNotFoundException;
import com.ngtai.library_api.persistence.dao.BookDAO;
import com.ngtai.library_api.persistence.entity.BookEntity;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookDAO repository;

    public BookService(BookDAO repository) {
        this.repository = repository;
    }

    public BookEntity createBook(BookEntity entity) {
        if (repository.findByTitle(entity.getTitle()) != null) {
            throw new BookAlreadyExistsException(entity.getTitle());
        }
        return repository.insert(entity);
    }

    public BookEntity findBookById(Long id) {
        BookEntity entity = repository.findById(id);
        if (entity == null) {
            throw new BookNotFoundException(id);
        }
        return entity;
    }

    public List<BookEntity> findAllBooks() {
        return repository.findAll();
    }

    public void updateBook(BookEntity entity) {
        BookEntity book = repository.findById(entity.getId());
        if (book == null) {
            throw new BookNotFoundException(entity.getId());
        }
        repository.update(entity);
    }

    public void deleteBook(Long id) {
        BookEntity book = repository.findById(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        repository.delete(id);
    }
}