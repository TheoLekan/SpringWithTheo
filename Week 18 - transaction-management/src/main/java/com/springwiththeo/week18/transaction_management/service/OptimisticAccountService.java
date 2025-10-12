package com.springwiththeo.week18.transaction_management.service;

import com.springwiththeo.week18.transaction_management.repository.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service@RequiredArgsConstructor
public class OptimisticAccountService {
    private final AccountRepo accountRepo;

    @Transactional
    public void deposit(Long id, double balance) {
        var account = accountRepo.findById(id).orElseThrow();
        double beforeBalance = account.getBalance();
        account.deposit(balance);
        System.out.println("Optimistic Locking: " + beforeBalance + " -> " + account.getBalance());
    }
}
