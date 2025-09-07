package com.springwiththeo.week13.data_access.repo;

import com.springwiththeo.week13.data_access.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
