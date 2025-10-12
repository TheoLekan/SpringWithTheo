package com.springwiththeo.week18.transaction_management.repository;

import com.springwiththeo.week18.transaction_management.model.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    /**
     *	•	PESSIMISTIC_WRITE → others can still read, but can’t update.<br>
     * 	•	PESSIMISTIC_READ → others can read, but not update or lock. <br>
     * 	•	PESSIMISTIC_FORCE_INCREMENT → behaves like optimistic but increments version.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Account a where a.id = :id")
    Account findAccountForUpdate(@Param("id") Long id);
}
