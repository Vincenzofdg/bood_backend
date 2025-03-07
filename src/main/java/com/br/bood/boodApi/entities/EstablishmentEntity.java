package com.br.bood.boodApi.entities;

import com.br.bood.boodApi.records.EstablishmentRecord;

import com.br.bood.boodApi.tools.IdGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "establishments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EstablishmentEntity {

    @Id
    @Column(nullable = false, updatable = false, unique = true, length = 20)
    private String id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String tags;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false)
    private Integer phone;

    @Column(nullable = false)
    private Integer document;

    @Column(nullable = false)
    private Float rate;

    private Boolean status;

    @Column(nullable = false, length = 255)
    private String preview_url;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public EstablishmentEntity(EstablishmentRecord payload) {
        this.id = IdGenerator.generate();
        this.name = payload.name();
        this.email = payload.email();
        this.tags = payload.tags();
        this.description = payload.description();
        this.phone = payload.phone();
        this.document = payload.document();
        this.rate = payload.rate();
        this.status = payload.status() != null ? payload.status() : false;
        this.preview_url = payload.preview_url();
    }
}
