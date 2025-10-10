package com.springwiththeo.week17.advanced_jpa;

import com.springwiththeo.week17.advanced_jpa.model.Author;
import com.springwiththeo.week17.advanced_jpa.model.Book;
import com.springwiththeo.week17.advanced_jpa.model.SalesRecord;
import com.springwiththeo.week17.advanced_jpa.repository.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import java.time.LocalDate;

@SpringBootApplication
public class AdvancedJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedJpaApplication.class, args);
    }

    @Bean(name = "init")
    public CommandLineRunner init(AuthorRepository authorRepository) {
        return args -> {

            Author author = new Author();
            author.setName("Robert C. Martin");

            Book book = new Book();
            book.setTitle("Clean Code");

            book.addSalesRecord(
                    new SalesRecord(5000, LocalDate.of(2025, 1, 1)),
                    new SalesRecord(1200, LocalDate.of(2025, 2, 1))
            );

            author.addBook(book);
            authorRepository.save(author);
        };
    }

    @Bean
    @DependsOn("init")
    public CommandLineRunner lazyloadAuthorsWithBooksAndSales(RunnerService runnerService) {
        return args -> {
            runnerService.lazyAuthorBooksAndSales();
            runnerService.entityGraphAuthorBooksAndSales();
        };

    }
}



