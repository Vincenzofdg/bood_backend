package com.br.bood.boodApi.domain.establishments.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "establishment_addresses")
public class AddressEntity {

    @Id
    @Column(nullable = false, updatable = false, unique = true, length = 50)
    private String id;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false, length = 100)
    private String neighborhood;

    @Column
    private Integer number;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 80)
    private String zip_code;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public AddressEntity() {}

//    public AddressEntity() {
//        this.id = IdGenerator.generate();
//        this.street
//    }
}
