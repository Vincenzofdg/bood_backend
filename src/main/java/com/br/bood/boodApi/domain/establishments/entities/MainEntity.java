package com.br.bood.boodApi.domain.establishments.entities;

import com.br.bood.boodApi.domain.establishments.records.MainRecord;
import com.br.bood.boodApi.shared.tools.StringFormater;
import com.br.bood.boodApi.shared.tools.IdGenerator;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
//import java.util.List;

@Entity
@Table(name = "establishments")
public class MainEntity {

    @Id
    @Column(nullable = false, updatable = false, unique = true, length = 50)
    private String id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String tags;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false, unique = true, length = 100)
    private String phone;

    @Column(nullable = false, unique = true, length = 100)
    private String document;

    @Column(nullable = false)
    private Float rate;

    private Boolean status;

    @Column(nullable = false, length = 255)
    private String previewUrl;

//    @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<MainEntity> establishment;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public MainEntity() {}

    public MainEntity(MainRecord payload) {
        this.id = IdGenerator.generate();
        this.name = payload.name();
        this.email = payload.email();
        this.tags = payload.tags();
        this.description = payload.description();
        this.phone = payload.phone();
        this.document = payload.document();
        this.rate = payload.rate();
        this.status = payload.status() != null ? payload.status() : false;
        this.previewUrl = payload.previewUrl();
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getTags() { return tags; }
    public String getDescription() { return description; }
    public String getPhone() { return phone; }
    public String getDocument() { return document; }
    public Float getRate() { return rate; }
    public Boolean getStatus() { return status; }
    public String getPreviewUrl() { return previewUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setTags(String tags) { this.tags = tags; }
    public void setDescription(String description) { this.description = description; }
    public void setPhone(String phone) {
        this.phone = StringFormater.phone(phone);
    }
    public void setDocument(String document) {
        String formatedDocument;

        if (document.length() > 5) {
            formatedDocument = StringFormater.cnpj(document);
        } else {
            formatedDocument = StringFormater.alvara(document);
        }

        this.document = formatedDocument;
    }
    public void setRate(Float rate) { this.rate = rate; }
    public void setStatus(Boolean status) { this.status = status; }
    public void setPreviewUrl(String previewUrl) { this.previewUrl = previewUrl; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MainEntity that = (MainEntity) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
