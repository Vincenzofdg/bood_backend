package com.br.bood.boodApi.controllers;

import com.br.bood.boodApi.entities.EstablishmentEntity;
import com.br.bood.boodApi.records.EstablishmentRecord;
import com.br.bood.boodApi.repositories.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/establishments")
public class EstablishmentController {

    @Autowired
    private EstablishmentRepository repository;

    @GetMapping
    public ResponseEntity<List<EstablishmentEntity>> getAllEstablishments() {
        List<EstablishmentEntity> result = repository.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentEntity> getEstablishmentById(@PathVariable String id) {
        Optional<EstablishmentEntity> result = repository.findById(id);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstablishmentRecord> createEstablishment(@RequestBody EstablishmentRecord payload) {
        repository.save(new EstablishmentEntity(payload));
        return ResponseEntity.ok(payload);
    }
}
