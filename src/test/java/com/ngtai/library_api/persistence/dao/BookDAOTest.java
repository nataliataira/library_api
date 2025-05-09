package com.ngtai.library_api.persistence.dao;

import com.ngtai.library_api.persistence.dao.impl.BookDAOImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.ngtai.library_api.persistence.entity.BookEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BookDAOTest {

    @Autowired
    private BookDAOImpl repository;

    @Test
    @DisplayName("Deve inserir e buscar um livro por ID")
    void insertAndFindById_shouldWork() {
        BookEntity book1 = new BookEntity();
        book1.setTitle("Clean Code");
        book1.setAuthor("Robert C. Martin");
        book1.setLoaned(false);

        BookEntity inserted1 = repository.insert(book1);
        assertNotNull(inserted1.getId());

        BookEntity book2 = new BookEntity();
        book2.setTitle("Clean Code");
        book2.setAuthor("Robert C. Martin");
        book2.setLoaned(false);

        BookEntity inserted2 = repository.insert(book2);
        assertNotNull(inserted2.getId());

        BookEntity loaded = repository.findById(inserted1.getId());
        assertNotNull(loaded);
        assertEquals("Clean Code", loaded.getTitle());
        assertEquals("Robert C. Martin", loaded.getAuthor());
        assertFalse(loaded.getLoaned());
    }
}