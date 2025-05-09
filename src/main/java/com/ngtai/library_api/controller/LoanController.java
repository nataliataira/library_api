package com.ngtai.library_api.controller;

import com.ngtai.library_api.persistence.entity.LoanEntity;
import com.ngtai.library_api.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/loans")
public class LoanController {
    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody LoanEntity entity) {
        service.createLoan(entity);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody LoanEntity entity) {
        service.updateLoan(entity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<LoanEntity>> findAll() {
        return ResponseEntity.ok(service.findAllLoans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanEntity> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findLoanById(id));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Void> returnLoan(@PathVariable Long id) {
        service.returnLoan(id);
        return ResponseEntity.ok().build();
    }

}
