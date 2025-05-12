package com.ngtai.library_api.exception;

public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException(Long id) {
        super("This Loan does not exist. Id: " + id);
    }
}
