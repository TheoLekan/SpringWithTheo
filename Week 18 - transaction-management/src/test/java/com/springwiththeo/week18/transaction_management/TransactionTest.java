package com.springwiththeo.week18.transaction_management;


import com.springwiththeo.week18.transaction_management.model.Account;
import com.springwiththeo.week18.transaction_management.model.AuditLog;
import com.springwiththeo.week18.transaction_management.repository.AccountRepo;
import com.springwiththeo.week18.transaction_management.repository.AuditRepo;
import com.springwiththeo.week18.transaction_management.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class TransactionTest {

    @Autowired private AccountRepo accountRepo;
    @Autowired private AccountService accountService;
    @Autowired private AuditRepo auditRepo;

    @Test
    void test_requires_new_allows_audit_to_commit_even_on_failure() {
        //Arrange
        Account fromAccount = new Account(); fromAccount.setBalance(100); fromAccount.setOwner("John");
        Account toAccount = new Account(); toAccount.setBalance(50); toAccount.setOwner("Jane");
        accountRepo.saveAll(List.of(fromAccount, toAccount));


        //Act
        try {
            accountService.transferMoneyAndFail(fromAccount.getId(), toAccount.getId(), 30);
        } catch (RuntimeException ignored) {}

        //Assert
        List<AuditLog> logs = auditRepo.findAll();
        assertFalse(logs.isEmpty(), "Audit log should contain an entry despite the failure in transfer");

        List<Account> accounts = accountRepo.findAll();
        assertEquals(100,accounts.get(0).getBalance(), "From account balance should remain unchanged due to rollback");


    }
}
