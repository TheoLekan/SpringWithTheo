package com.springwiththeo.week18.transaction_management.service;

import com.springwiththeo.week18.transaction_management.model.Account;
import com.springwiththeo.week18.transaction_management.repository.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Thread.sleep;

@Service
@RequiredArgsConstructor
public class IsolationService {
    private final AccountRepo accountRepo;


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void withdrawReadCommitted(Long accountId, double amount) {
        Account account= accountRepo.findById(accountId).orElseThrow();
        double beforeBalance = account.getBalance();
        sleep(2000); // Simulate delay
        account.withdraw(amount);
        System.out.println("READ_COMMITTED: " + beforeBalance + " -> " + account.getBalance());
    }

    //If it reads the balance no other transaction can read or write until this transaction is complete
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void withdrawRepeatableRead(Long accountId, double amount) {
        Account account= accountRepo.findById(accountId).orElseThrow();
        double beforeBalance = account.getBalance();
        sleep(2000); // Simulate delay
        account.withdraw(amount);
        System.out.println("Repeatable Read: " + beforeBalance + " -> " + account.getBalance());
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {}
    }
}
