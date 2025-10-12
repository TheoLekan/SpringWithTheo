package com.springwiththeo.week18.transaction_management;

import com.springwiththeo.week18.transaction_management.model.Account;
import com.springwiththeo.week18.transaction_management.repository.AccountRepo;
import com.springwiththeo.week18.transaction_management.service.OptimisticAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class OptimisticLockingTest {
    
    @Autowired AccountRepo accountRepo;
   @Autowired OptimisticAccountService optimisticAccountService;


    @Test
    void testOptimisticChecking() throws InterruptedException {
        Account johnDoe = accountRepo.save(new Account("John Doe", 1000));

        Runnable task1 = () -> optimisticAccountService.deposit(johnDoe.getId(), 50);
        Runnable task2 = () -> optimisticAccountService.deposit(johnDoe.getId(), 70);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();thread2.start();
        thread1.join(); thread2.join();
        Account finalState = accountRepo.findById(johnDoe.getId()).get();
        System.out.println("Final balance = " + finalState.getBalance());
        System.out.println("Final version = " + finalState.getVersion());
    }
}
