package com.springwiththeo.week18.transaction_management.service;

import com.springwiththeo.week18.transaction_management.model.Account;
import com.springwiththeo.week18.transaction_management.repository.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepo accountRepo;
    private final AuditService auditService;


    @Transactional
    public void transferMoney(Long fromId, Long toId, double amount) {
        Account fromAccount = accountRepo.findById(fromId).orElseThrow(() -> new RuntimeException("Account not found"));
        Account toAccount = accountRepo.findById(toId).orElseThrow(() -> new RuntimeException("Account not found"));

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);


        auditService.logTransfer(fromId, toId, amount);
    }


    @Transactional
    public void transferMoneyAndFail(Long fromId, Long toId, double amount) {
        Account fromAccount = accountRepo.findById(fromId).orElseThrow(() -> new RuntimeException("Account not found"));
        Account toAccount = accountRepo.findById(toId).orElseThrow(() -> new RuntimeException("Account not found"));

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);


        auditService.logTransfer(fromId, toId, amount);

        throw new RuntimeException("Simulated failure after transfer");
    }
}
