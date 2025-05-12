package com.ngtai.library_api.exception;

public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException(String title) {
        super("This Book already exists. Title: " + title);
    }
}
