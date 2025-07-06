package com.springwiththeo.week3.spring_web_basics.controller;

import com.springwiththeo.week3.spring_web_basics.model.Employee;
import com.springwiththeo.week3.spring_web_basics.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>> allEmployees() {
        return ResponseEntity.ok(employeeRepo.findAll());
    }

    @GetMapping("/employees/count")
    public ResponseEntity<Long> countEmployees() {
        return ResponseEntity.ok(employeeRepo.count());
    }

    @GetMapping("/employees/positions")
    public ResponseEntity<Iterable<String>> allPositions() {

        return ResponseEntity.ok(
                employeeRepo.findAll().stream()
                        .map(Employee::getPosition)
                        .distinct()
                        .toList()
        );
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long id) {
        return employeeRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@Validated @RequestBody Employee employee) {
        Employee savedEmployee = employeeRepo.save(employee);
        return ResponseEntity.status(201).body(savedEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {

        employeeRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee updatedEmployee) {
        return employeeRepo.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setPosition(updatedEmployee.getPosition());
                    return ResponseEntity.ok().body(employeeRepo.save(employee));
                })
                .orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }
}
