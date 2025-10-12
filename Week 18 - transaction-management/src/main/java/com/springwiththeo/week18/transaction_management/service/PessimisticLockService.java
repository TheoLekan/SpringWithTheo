package com.springwiththeo.week18.transaction_management.service;

import com.springwiththeo.week18.transaction_management.model.Account;
import com.springwiththeo.week18.transaction_management.repository.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Thread.sleep;

@Service
@RequiredArgsConstructor
public class PessimisticLockService {

    private final AccountRepo accountRepo;

    @Transactional
    public void safeWithdrawal(Long id, double amount) {
        System.out.println(Thread.currentThread().getName() + " trying to lock...");
        Account account = accountRepo.findAccountForUpdate(id);
        System.out.println(Thread.currentThread().getName() + " acquired lock ✅");

        double beforeBalance = account.getBalance();
        account.withdraw(amount);
        sleep(3000); // Simulate delay
        System.out.println("Pessimistic Locking: " + beforeBalance + " -> " + account.getBalance());
        System.out.println(Thread.currentThread().getName() + " releasing lock...");

    }
   void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }

}
