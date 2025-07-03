package com.springwiththeo.week3.spring_web_basics.repository;

import com.springwiththeo.week3.spring_web_basics.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
