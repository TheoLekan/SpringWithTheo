package com.springwiththeo.week18.transaction_management.service;

import com.springwiththeo.week18.transaction_management.model.AuditLog;
import com.springwiththeo.week18.transaction_management.repository.AuditRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepo accountRepo;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logTransfer(Long fromId, Long toId, double amount) {
        // Simulate logging the transfer
       accountRepo.save(
               new AuditLog("Logged transfer of " + amount + " from account " + fromId + " to account " + toId)
       );
    }

}
