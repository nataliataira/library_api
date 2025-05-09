package com.ngtai.library_api.controller;

import com.ngtai.library_api.persistence.entity.LoanAuditEntity;
import com.ngtai.library_api.service.LoanAuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/loan_audits")
public class LoanAuditController {
    private final LoanAuditService service;

    public LoanAuditController(LoanAuditService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LoanAuditEntity>> findAll() {
        return ResponseEntity.ok(service.findAllAuditLoans());
    }
}
