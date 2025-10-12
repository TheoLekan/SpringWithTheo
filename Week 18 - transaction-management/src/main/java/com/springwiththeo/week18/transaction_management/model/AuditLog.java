package com.springwiththeo.week18.transaction_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class AuditLog {
    @Id
    @GeneratedValue
    private Long id;
    private String message;
    private LocalDateTime timestamp;

    public AuditLog(String message) {
        this.message = message;
    }

    @PrePersist
    public void updateTimeStamp () {
        this.timestamp = LocalDateTime.now();
    }


}
