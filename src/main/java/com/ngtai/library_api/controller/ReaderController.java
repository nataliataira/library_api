package com.ngtai.library_api.controller;

import com.ngtai.library_api.persistence.entity.ReaderEntity;
import com.ngtai.library_api.service.ReaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {
    private final ReaderService service;

    public ReaderController(ReaderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ReaderEntity entity) {
        service.createReader(entity);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ReaderEntity entity) {
        service.updateReader(entity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteReader(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReaderEntity>> findAll() {
        return ResponseEntity.ok(service.findAllReaders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderEntity> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findReaderById(id));
    }
}
