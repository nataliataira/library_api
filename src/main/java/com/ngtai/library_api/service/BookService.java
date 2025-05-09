package com.ngtai.library_api.service;

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
            throw new IllegalArgumentException("This Book name already exists.");
        }
        return repository.insert(entity);
    }

    public BookEntity findBookById(Long id) {
        BookEntity entity = repository.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("This Book does not exist.");
        }
        return entity;
    }

    public List<BookEntity> findAllBooks() {
        List<BookEntity> books = repository.findAll();
        if (books.isEmpty()) {
            throw new IllegalArgumentException("No books found.");
        }
        return books;
    }

    public void updateBook(BookEntity entity) {
        BookEntity book = repository.findById(entity.getId());
        if (book == null) {
            throw new IllegalArgumentException("This Book does not exist.");
        }
        repository.update(entity);
    }

    public void deleteBook(Long id) {
        BookEntity book = repository.findById(id);
        if (book == null) {
            throw new IllegalArgumentException("This Book does not exist.");
        }
        repository.delete(id);
    }
}