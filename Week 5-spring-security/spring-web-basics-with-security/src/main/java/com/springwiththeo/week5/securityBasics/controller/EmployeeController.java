package com.springwiththeo.week5.securityBasics.controller;

import com.springwiththeo.week5.securityBasics.model.Employee;
import com.springwiththeo.week5.securityBasics.repository.EmployeeRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    @GetMapping()
    public ResponseEntity<Iterable<Employee>> allEmployees() {
        return ResponseEntity.ok(employeeRepo.findAll());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countEmployees() {
        return ResponseEntity.ok(employeeRepo.count());
    }

    @GetMapping("/positions")
    public ResponseEntity<Iterable<String>> allPositions() {

        return ResponseEntity.ok(
                employeeRepo.findAll().stream()
                        .map(Employee::getPosition)
                        .distinct()
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long id) {
        return employeeRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeRepo.save(employee);
        return ResponseEntity.status(201).body(savedEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        if (!employeeRepo.existsById(id)) {
            throw new RuntimeException("Employee with ID " + id + " does not exist.");
        }
        employeeRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee updatedEmployee) {
        if (!employeeRepo.existsById(id)) {
            throw new RuntimeException("Employee with ID " + id + " does not exist.");
        }
        return employeeRepo.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setPosition(updatedEmployee.getPosition());
                    return ResponseEntity.ok().body(employeeRepo.save(employee));
                }).get();
    }
}
