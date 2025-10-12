package com.springwiththeo.week18.transaction_management;

import com.springwiththeo.week18.transaction_management.model.Account;
import com.springwiththeo.week18.transaction_management.repository.AccountRepo;
import com.springwiththeo.week18.transaction_management.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class RollbackTest {

    @Autowired AccountService accountService;
    @Autowired AccountRepo accountRepo;

    @Test
    void rollbackOnUncheckedException() {

        Account alice = accountRepo.save(new Account("Alice", 1000));
        Account bob = accountRepo.save(new Account("Bob", 1000));

        assertThrows(RuntimeException.class, () -> {
            accountService.transferMoneyAndFail(alice.getId(), bob.getId(), 100);
        });

        Account aliceAfter = accountRepo.findById(alice.getId()).get();
        Account bobAfter = accountRepo.findById(bob.getId()).get();

        System.out.println("Transferr balance from "+ aliceAfter.getBalance() + " to " + bobAfter.getBalance());
        assertTrue(aliceAfter.getBalance() == 1000);
        assertTrue(bobAfter.getBalance() == 1000);

    }

    @Test
    void noRollbackOnUncheckedException() {
        Account alice = accountRepo.save(new Account("Alice", 1000));
        Account bob = accountRepo.save(new Account("Bob", 1000));

        assertThrows(RuntimeException.class, () -> {
            accountService.transferMoneyAndFailCustomException(alice.getId(), bob.getId(), 100);
        });

        Account aliceAfter = accountRepo.findById(alice.getId()).get();
        Account bobAfter = accountRepo.findById(bob.getId()).get();

        System.out.println("Transferr balance from "+ aliceAfter.getBalance() + " to " + bobAfter.getBalance());
        assertFalse(aliceAfter.getBalance() == 1000);
        assertFalse(bobAfter.getBalance() == 1000);
    }

    @Test
    void noRollbackCheckedException() {
        Account alice = accountRepo.save(new Account("Alice", 1000));
        Account bob = accountRepo.save(new Account("Bob", 1000));

        assertThrows(IOException.class, () -> {
            accountService.transferMoneyAndFailCheckException(alice.getId(), bob.getId(), 100);
        });

        Account aliceAfter = accountRepo.findById(alice.getId()).get();
        Account bobAfter = accountRepo.findById(bob.getId()).get();

        System.out.println("Transferr balance from "+ aliceAfter.getBalance() + " to " + bobAfter.getBalance());
        assertFalse(aliceAfter.getBalance() == 1000);
        assertFalse(bobAfter.getBalance() == 1000);
    }
}
