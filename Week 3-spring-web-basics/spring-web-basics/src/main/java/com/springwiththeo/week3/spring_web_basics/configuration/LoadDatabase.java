package com.springwiththeo.week3.spring_web_basics.configuration;

import com.springwiththeo.week3.spring_web_basics.model.Employee;
import com.springwiththeo.week3.spring_web_basics.repository.EmployeeRepo;
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
                    new Employee("John Doe", "Software Engineer"),
                    new Employee("Jane Smith", "Project Manager"),
                    new Employee("Alice Johnson", "Data Analyst")
            )).forEach(employee -> log.info("Preloading {}", employee));
        };
    }

}
