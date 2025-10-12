package com.springwiththeo.week18.transaction_management;

import com.springwiththeo.week18.transaction_management.model.Account;
import com.springwiththeo.week18.transaction_management.repository.AccountRepo;
import com.springwiththeo.week18.transaction_management.service.PessimisticLockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class PessimisticLockingTest {

    @Autowired private AccountRepo accountRepo;
    @Autowired private PessimisticLockService pessimisticLockService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void testPessimisticLock() throws InterruptedException {
        Account johnDoe = accountRepo.save(new Account("John Doe", 1000));
        Runnable runnable1 = () -> pessimisticLockService.safeWithdrawal(1L, 200);
        Runnable runnable2 = () -> pessimisticLockService.safeWithdrawal(1L, 300);

//        jdbcTemplate.execute("SET LOCK_TIMEOUT 10000");

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start(); thread2.start();
        thread1.join(); thread2.join();
        System.out.println("Final Balance"+ accountRepo.findById(johnDoe.getId()).get().getBalance());


    }
}
