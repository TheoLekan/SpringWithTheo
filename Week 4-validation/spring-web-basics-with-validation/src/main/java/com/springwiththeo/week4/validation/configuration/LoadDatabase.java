package com.springwiththeo.week4.validation.configuration;

import com.springwiththeo.week4.validation.model.Employee;
import com.springwiththeo.week4.validation.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepo employeeRepo) {
        return args -> {
            // Initialize the database with some sample data
            employeeRepo.saveAll(List.of(
                    new Employee("John Doe", "Software Engineer","JohnDoe@gmail.com"),
                    new Employee("Jane Smith", "Project Manager","JaneSmith@yahoo.com"),
                    new Employee("Alice Johnson", "Data Analyst", "AliceJohnson@icloud.com")
            )).forEach(employee -> log.info("Preloading {}", employee));
        };
    }

}
