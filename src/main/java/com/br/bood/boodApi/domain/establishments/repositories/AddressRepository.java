package com.br.bood.boodApi.domain.establishments.repositories;

import com.br.bood.boodApi.domain.establishments.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, String> {
    Optional<AddressEntity> findByEstablishment_Id(String establishmentId);
    boolean existsByEstablishment_Id(String establishmentId);
    void deleteByEstablishment_Id(String establishmentId);
}


//📌 Explicação:
//
//findByEstablishment_Id(String establishmentId):
//establishment → nome do atributo na AddressEntity
//_Id → acessa o id dentro de MainEntity