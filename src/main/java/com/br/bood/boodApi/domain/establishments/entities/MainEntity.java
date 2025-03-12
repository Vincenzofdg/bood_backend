package com.br.bood.boodApi.domain.establishments.entities;

import com.br.bood.boodApi.domain.establishments.records.MainRecord;
import com.br.bood.boodApi.shared.tools.FormatDocument;
import com.br.bood.boodApi.shared.tools.FormatPhone;
import com.br.bood.boodApi.shared.tools.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

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
    private String preview_url;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

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
        this.preview_url = payload.preview_url();
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
    public String getPreview_url() { return preview_url; }
    public LocalDateTime getCreated_at() { return created_at; }
    public LocalDateTime getUpdated_at() { return updated_at; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setTags(String tags) { this.tags = tags; }
    public void setDescription(String description) { this.description = description; }
    public void setPhone(String phone) {
        this.phone = FormatPhone.format(phone);;
    }
    public void setDocument(String document) {
        String formatedDocument;

        if (document.length() > 5) {
            formatedDocument = FormatDocument.cnpj(document);
        } else {
            formatedDocument = FormatDocument.alvara(document);
        }

        this.document = formatedDocument;
    }
    public void setRate(Float rate) { this.rate = rate; }
    public void setStatus(Boolean status) { this.status = status; }
    public void setPreview_url(String preview_url) { this.preview_url = preview_url; }

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
