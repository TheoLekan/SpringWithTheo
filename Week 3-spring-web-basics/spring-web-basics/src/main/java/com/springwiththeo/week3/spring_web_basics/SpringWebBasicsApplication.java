package com.springwiththeo.week3.spring_web_basics;

import com.springwiththeo.week3.spring_web_basics.model.Employee;
import com.springwiththeo.week3.spring_web_basics.repository.EmployeeRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringWebBasicsApplication implements CommandLineRunner {

	private final EmployeeRepo employeeRepo;

	public SpringWebBasicsApplication(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringWebBasicsApplication.class, args);
	}


	@Override
	public void run(String... args) {
		// Initialize the database with some sample data
		List<Employee> employees = List.of(
				new Employee("John Doe", "Software Engineer"),
				new Employee("Jane Smith", "Project Manager"),
				new Employee("Alice Johnson", "Data Analyst")
		);
		employeeRepo.saveAll(employees);
	}
}
