package com.br.bood.boodApi.domain.establishments.entities;

import com.br.bood.boodApi.domain.establishments.records.AddressRecord;
import com.br.bood.boodApi.shared.tools.IdGenerator;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "establishment_addresses")
public class AddressEntity {

    @Id
    @Column(nullable = false, updatable = false, unique = true, length = 50)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "establishment_id", nullable = false)
    private MainEntity establishment;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false, length = 100)
    private String neighborhood;

    @Column
    private Integer number;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 80)
    private String zipCode;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public AddressEntity() {}

    public AddressEntity(MainEntity establishment, AddressRecord payload) {
        this.id = IdGenerator.generate();
        this.establishment = establishment;
        this.street = payload.street();
        this.neighborhood = payload.neighborhood();
        this.number = payload.number();
        this.city = payload.city();
        this.zipCode = payload.zipCode();
    }

    // Getters
    public String getId() { return id; }
    public MainEntity getEstablishment() { return establishment; }
    public String getStreet() { return street; }
    public String getNeighborhood() { return neighborhood; }
    public Integer getNumber() { return number; }
    public String getCity() { return city; }
    public String getZipCode() { return zipCode; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setStreet(String street) { this.street = street; }
    public void setNeighborhood(String neighborhood) { this.neighborhood = neighborhood; }
    public void setNumber(Integer number) { this.number = number; }
    public void setCity(String city) { this.city = city; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AddressEntity that = (AddressEntity) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
