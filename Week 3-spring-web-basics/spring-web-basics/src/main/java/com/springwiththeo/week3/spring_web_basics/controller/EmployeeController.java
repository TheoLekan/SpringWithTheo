package com.springwiththeo.week3.spring_web_basics.controller;

import com.springwiththeo.week3.spring_web_basics.model.Employee;
import com.springwiththeo.week3.spring_web_basics.repository.EmployeeRepo;
import jakarta.websocket.server.PathParam;
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
    public Employee createEmployee(@PathParam("name") String name, @PathParam("position") String position) {
        return employeeRepo.save(new Employee(name, position));
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeRepo.deleteById(id);
    }
}
