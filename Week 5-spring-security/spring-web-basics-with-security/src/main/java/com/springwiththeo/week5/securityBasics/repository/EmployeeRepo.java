package com.springwiththeo.week5.securityBasics.repository;

import com.springwiththeo.week5.securityBasics.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
