package com.springwiththeo.week18.transaction_management;

import com.springwiththeo.week18.transaction_management.model.Account;
import com.springwiththeo.week18.transaction_management.repository.AccountRepo;
import com.springwiththeo.week18.transaction_management.service.AccountService;
import com.springwiththeo.week18.transaction_management.service.IsolationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class IsolationTest {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private IsolationService isolationService;

    @Test
    void testIsolationLevelsReadCommitted() throws InterruptedException{
        Long id = accountRepo.save(new Account("John Doe", 100)).getId();

        Thread t1 = new Thread(() -> isolationService.withdrawReadCommitted(id, 50));
        Thread t2 = new Thread(() -> isolationService.withdrawReadCommitted(id, 20));

        t1.start();
        t2.start();
        t1.join(); t2.join();

        double finalBalance = accountRepo.findById(id).orElseThrow().getBalance();
        System.out.println("Final Balance after READ_COMMITTED transactions: " + finalBalance);
    }

    @Test
    void testIsolationLevelsRepeatableRead() throws InterruptedException{
        Long id = accountRepo.save(new Account("John Doe", 100)).getId();

        Thread t1 = new Thread(() -> isolationService.withdrawRepeatableRead(id, 50));
        Thread t2 = new Thread(() -> isolationService.withdrawRepeatableRead(id, 50));

        t1.start();
        t2.start();
        t1.join(); t2.join();

        double finalBalance = accountRepo.findById(id).orElseThrow().getBalance();
        System.out.println("Final Balance after REPEATABLE READ transactions: " + finalBalance);
    }


}
