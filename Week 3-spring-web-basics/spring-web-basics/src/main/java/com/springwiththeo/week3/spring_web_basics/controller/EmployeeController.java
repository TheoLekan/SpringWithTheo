package com.springwiththeo.week3.spring_web_basics.controller;

import com.springwiththeo.week3.spring_web_basics.model.Employee;
import com.springwiththeo.week3.spring_web_basics.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    @GetMapping("/employees")
    public Iterable<Employee> allEmployees() {
        return employeeRepo.findAll();
    }

    @GetMapping("/employees/count")
    public long countEmployees() {
        return employeeRepo.count();
    }

    @GetMapping("/employees/positions")
    public Iterable<String> allPositions() {
        return employeeRepo.findAll().stream()
                .map(Employee::getPosition)
                .distinct()
                .toList();
    }

    @GetMapping("/employee/{id}")
    public Employee findEmployeeById(@PathVariable("id") Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepo.save(employee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeRepo.deleteById(id);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee updatedEmployee) {
        return employeeRepo.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setPosition(updatedEmployee.getPosition());
                    return employeeRepo.save(employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }
}
