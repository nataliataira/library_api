package com.ngtai.library_api.service;

import com.ngtai.library_api.exception.BookAlreadyLoanedException;
import com.ngtai.library_api.exception.BookNotFoundException;
import com.ngtai.library_api.exception.LoanNotFoundException;
import com.ngtai.library_api.exception.ReaderNotFoundException;
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

        if ( book == null) {
            throw new BookNotFoundException(entity.getBookId());
        } else if (reader == null) {
            throw new ReaderNotFoundException(entity.getReaderId());
        }

        if (Boolean.TRUE.equals(book.getLoaned())) {
            throw new BookAlreadyLoanedException(book.getId());
        }

        book.setLoaned(true);
        loanRepository.insert(entity);
    }

    public LoanEntity findLoanById(Long id) {
        LoanEntity entity = loanRepository.findById(id);
        if (entity == null) {
            throw new LoanNotFoundException(id);
        }
        return entity;
    }

    public List<LoanEntity> findAllLoans() {
        return loanRepository.findAll();
    }

    public void updateLoan(LoanEntity entity) {
        LoanEntity oldEntity = loanRepository.findById(entity.getId());
        if (oldEntity == null) {
            throw new LoanNotFoundException(entity.getId());
        }
        loanRepository.update(entity);
    }

    public void deleteLoan(Long id) {
        LoanEntity loan = loanRepository.findById(id);
        if (loan == null) {
            throw new LoanNotFoundException(id);
        }
        loanRepository.delete(id);
    }

    public void returnLoan(Long id) {
        LoanEntity loan = loanRepository.findById(id);
        if (loan == null) {
            throw new LoanNotFoundException(id);
        }
        loan.setReturnDate(OffsetDateTime.now());
        loanRepository.update(loan);

        BookEntity book = bookRepository.findById(loan.getBookId());
        if (book == null) {
            throw new BookNotFoundException(loan.getBookId());
        }
        book.setLoaned(false);
        bookRepository.update(book);
    }
}
