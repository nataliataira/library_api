package com.ngtai.library_api.exception;

public class ReaderNotFoundException extends RuntimeException {

    public ReaderNotFoundException(Long id) {
        super("This Reader does not exist. Id: " + id);
    }
}
