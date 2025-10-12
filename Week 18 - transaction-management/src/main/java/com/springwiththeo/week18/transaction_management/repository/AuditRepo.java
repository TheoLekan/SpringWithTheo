package com.springwiththeo.week18.transaction_management.repository;

import com.springwiththeo.week18.transaction_management.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepo extends JpaRepository<AuditLog, Long> {
}
