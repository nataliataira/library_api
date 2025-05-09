package com.ngtai.library_api.service;

import com.ngtai.library_api.persistence.dao.BookDAO;
import com.ngtai.library_api.persistence.dao.LoanDAO;
import com.ngtai.library_api.persistence.dao.ReaderDAO;
import com.ngtai.library_api.persistence.entity.BookEntity;
import com.ngtai.library_api.persistence.entity.LoanEntity;
import com.ngtai.library_api.persistence.entity.ReaderEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class LoanService {

    private final LoanDAO loanRepository;
    private final BookDAO bookRepository;
    private final ReaderDAO readerRepository;

    public LoanService(LoanDAO loanRepository, BookDAO bookRepository, ReaderDAO readerRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public void createLoan(LoanEntity entity) {
        BookEntity book = bookRepository.findById(entity.getBookId());
        ReaderEntity reader = readerRepository.findById(entity.getReaderId());
        if ( book == null || reader == null) {
            throw new IllegalArgumentException("This Book or Reader does not exist.");
        }
        if (!book.getLoaned()) {
            book.setLoaned(true);
            loanRepository.insert(entity);
        }
        throw new IllegalArgumentException("This Book already loaned.");
    }

    public LoanEntity findLoanById(Long id) {
        LoanEntity entity = loanRepository.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("This Loan does not exist.");
        }
        return entity;
    }

    public List<LoanEntity> findAllLoans() {
        List<LoanEntity> loans = loanRepository.findAll();
        if (loans.isEmpty()) {
            throw new IllegalArgumentException("No loans found.");
        }
        return loans;
    }

    public void updateLoan(LoanEntity entity) {
        LoanEntity oldEntity = loanRepository.findById(entity.getId());
        if (oldEntity == null) {
            throw new IllegalArgumentException("This loan does not exist.");
        }
        loanRepository.update(entity);
    }

    public void deleteLoan(Long id) {
        LoanEntity loan = loanRepository.findById(id);
        if (loan == null) {
            throw new IllegalArgumentException("This Loan does not exist.");
        }
        loanRepository.delete(id);
    }

    public void returnLoan(Long id) {
        LoanEntity loan = loanRepository.findById(id);
        if (loan == null) {
            throw new IllegalArgumentException("This Loan does not exist.");
        }
        loan.setReturnDate(OffsetDateTime.now());
        loanRepository.update(loan);

        BookEntity book = bookRepository.findById(loan.getBookId());
        if (book == null) {
            throw new IllegalArgumentException("This Book does not exist.");
        }
        book.setLoaned(false);
        bookRepository.update(book);
    }
}
