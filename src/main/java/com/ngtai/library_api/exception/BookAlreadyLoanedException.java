package com.ngtai.library_api.exception;

public class BookAlreadyLoanedException extends RuntimeException {

    public BookAlreadyLoanedException(Long bookId) {
        super("Book already loaned: " + bookId);
    }
}
