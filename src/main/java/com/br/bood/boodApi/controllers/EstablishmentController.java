package com.br.bood.boodApi.controllers;

import com.br.bood.boodApi.entities.EstablishmentEntity;
import com.br.bood.boodApi.records.EstablishmentRecord;
import com.br.bood.boodApi.repositories.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> createEstablishment(@RequestBody EstablishmentRecord payload) {
        try {
            EstablishmentEntity newObjOnDB = new EstablishmentEntity(payload);
            repository.save(newObjOnDB);
            return ResponseEntity.status(HttpStatus.CREATED).body(newObjOnDB);
        } catch (DataIntegrityViolationException err) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: E-mail/document already on Database.");
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEstablishment(@PathVariable String id, @RequestBody Map<String, Object> payload) {
        Optional<EstablishmentEntity> establishmentFromDB = repository.findById(id);

        if (establishmentFromDB.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Estabelecimento não encontrado.");
        }

        EstablishmentEntity existingEntity = establishmentFromDB.get();

        payload.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingEntity.setName((String) value);
                    break;
                case "email":
                    existingEntity.setEmail((String) value);
                    break;
                case "tags":
                    existingEntity.setTags((String) value);
                    break;
                case "description":
                    existingEntity.setDescription((String) value);
                    break;
                case "phone":
                    existingEntity.setPhone((String) value);
                    break;
                case "document":
                    existingEntity.setDocument((String) value);
                    break;
                case "rate":
                    existingEntity.setRate(Float.valueOf((String) value));
                    break;
                case "status":
                    existingEntity.setStatus(Boolean.valueOf((String) value));
                    break;
                case "preview_url":
                    existingEntity.setPreview_url((String) value);
                    break;
            }
        });

        repository.save(existingEntity);
        return ResponseEntity.ok(existingEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstablishment(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Establishment Found.");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
