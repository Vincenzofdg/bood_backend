package com.br.bood.boodApi.domain.establishments.controllers;

import com.br.bood.boodApi.domain.establishments.entities.MainEntity;
import com.br.bood.boodApi.domain.establishments.records.MainRecord;
import com.br.bood.boodApi.domain.establishments.repositories.MainRepository;
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
public class MainController {

    @Autowired
    private MainRepository repository;

    @GetMapping
    public ResponseEntity<List<MainEntity>> getAllEstablishments() {
        List<MainEntity> result = repository.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainEntity> getEstablishmentById(@PathVariable String id) {
        Optional<MainEntity> result = repository.findById(id);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createEstablishment(@RequestBody MainRecord payload) {
        try {
            MainEntity newObjOnDB = new MainEntity(payload);
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
        Optional<MainEntity> establishmentFromDB = repository.findById(id);

        if (establishmentFromDB.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Estabelecimento não encontrado.");
        }

        MainEntity existingEntity = establishmentFromDB.get();

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
                case "previewUrl":
                    existingEntity.setPreviewUrl((String) value);
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
