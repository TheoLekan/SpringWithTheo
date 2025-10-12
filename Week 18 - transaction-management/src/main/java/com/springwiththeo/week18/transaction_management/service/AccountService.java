package com.springwiththeo.week18.transaction_management.service;

import com.springwiththeo.week18.transaction_management.exceptions.BusinessLogicException;
import com.springwiththeo.week18.transaction_management.model.Account;
import com.springwiththeo.week18.transaction_management.repository.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepo accountRepo;
    private final AuditService auditService;


    @Transactional
    public void transferMoney(Long fromId, Long toId, double amount) {
        Account fromAccount = accountRepo.findById(fromId).orElseThrow(() -> new RuntimeException("Account not found"));
        Account toAccount = accountRepo.findById(toId).orElseThrow(() -> new RuntimeException("Account not found"));

        System.out.println("Transferring Money from " + fromAccount.getBalance() + " to " + toAccount.getBalance());
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);
        System.out.println("Transferred Money from " + fromAccount.getBalance() + " to " + toAccount.getBalance());
        auditService.logTransfer(fromId, toId, amount);
    }


    @Transactional
    public void transferMoneyAndFail(Long fromId, Long toId, double amount) {
        transferMoney(fromId, toId, amount);
        throw new RuntimeException("Simulated failure after transfer");
    }

    @Transactional(noRollbackFor = BusinessLogicException.class)
    public void transferMoneyAndFailCustomException(Long fromId, Long toId, double amount) throws BusinessLogicException {
        transferMoney(fromId, toId, amount);
        throw new BusinessLogicException("Simulated failure with customer Unchecked Exception after transfer");
    }

    @Transactional
    public void transferMoneyAndFailCheckException(Long fromId, Long toId, double amount) throws IOException {
        transferMoney(fromId, toId, amount);
        throw new IOException("Simulated failure with customer Unchecked Exception after transfer");
    }










}
