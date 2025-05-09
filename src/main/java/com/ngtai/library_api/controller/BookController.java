package com.ngtai.library_api.controller;

import com.ngtai.library_api.persistence.entity.BookEntity;
import com.ngtai.library_api.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookEntity> create(@RequestBody BookEntity entity) {
        return ResponseEntity.ok(service.createBook(entity));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody BookEntity entity) {
        service.updateBook(entity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BookEntity>> findAll() {
        return ResponseEntity.ok(service.findAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findBookById(id));
    }
}
