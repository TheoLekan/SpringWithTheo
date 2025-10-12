package com.springwiththeo.week18.transaction_management.repository;

import com.springwiththeo.week18.transaction_management.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {}
