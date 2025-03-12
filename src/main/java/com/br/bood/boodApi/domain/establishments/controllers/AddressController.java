package com.br.bood.boodApi.domain.establishments.controllers;

import com.br.bood.boodApi.domain.establishments.entities.AddressEntity;
import com.br.bood.boodApi.domain.establishments.entities.MainEntity;
import com.br.bood.boodApi.domain.establishments.records.AddressRecord;
import com.br.bood.boodApi.domain.establishments.repositories.AddressRepository;
import com.br.bood.boodApi.domain.establishments.repositories.MainRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/establishments/address")
public class AddressController {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private MainRepository establishmentRepository;

    @GetMapping
    public ResponseEntity<List<AddressEntity>> getAllAddresses() {
        List<AddressEntity> result = repository.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{establishmentId}")
    public ResponseEntity<AddressEntity> getAddressByEstablishmentId(@PathVariable String establishmentId) {
        Optional<AddressEntity> result = repository.findByEstablishment_Id(establishmentId);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{establishmentId}")
    public ResponseEntity<?> updateAddress(@PathVariable String establishmentId, @RequestBody Map<String, Object> payload) {
        Optional<AddressEntity> target = repository.findByEstablishment_Id(establishmentId);

        if (target.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Address Found");
        }

        AddressEntity targetEntity = target.get();

        payload.forEach((key, value) -> {
            switch (key) {
                case "street":
                    targetEntity.setStreet((String) value);
                    break;
                case "neighborhood":
                    targetEntity.setNeighborhood((String) value);
                    break;
                case "number":
                    targetEntity.setNumber((Integer) value);
                    break;
                case "city":
                    targetEntity.setCity((String) value);
                    break;
                case "ziCode":
                    targetEntity.setZipCode((String) value);
                    break;
            }
        });

        repository.save(targetEntity);
        return ResponseEntity.ok(targetEntity);
    }

    @Transactional
    @PostMapping("/{establishmentId}")
    public ResponseEntity<?> createAddress(@PathVariable String establishmentId, @RequestBody AddressRecord payload) {
        Optional<MainEntity> establishment = establishmentRepository.findById(establishmentId);

        if (establishment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Establishment not found.");
        }

        AddressEntity newAddress = new AddressEntity(establishment.get(), payload);

        repository.save(newAddress);

        return ResponseEntity.status(HttpStatus.CREATED).body(newAddress.getId());
    }

    @Transactional // 🔥 Garante que o delete ocorra dentro de uma transação
    @DeleteMapping("/{establishmentId}")
    public ResponseEntity<?> deleteEstablishmentAddress(@PathVariable String establishmentId) {
        if (!repository.existsByEstablishment_Id(establishmentId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Address Found.");
        }
        repository.deleteByEstablishment_Id(establishmentId);
        return ResponseEntity.noContent().build();
    }
}
