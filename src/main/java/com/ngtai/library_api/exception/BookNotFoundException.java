package com.ngtai.library_api.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("This Book does not exist. Id: " + id);
    }
}
