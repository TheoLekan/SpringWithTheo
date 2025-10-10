package com.springwiththeo.week17.advanced_jpa.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class Auditable {
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @PrePersist
    public void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastModifiedDate = LocalDateTime.now();
    }

}
