package com.springwiththeo.week13.data_access;

import com.springwiththeo.week13.data_access.model.Book;
import com.springwiththeo.week13.data_access.repo.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootApplication
public class SpringDataAccessProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataAccessProjectApplication.class, args);
	}

    @Bean
    CommandLineRunner init(BookRepository bookRepository) {
        return args -> {
            // Initialize the database with some books if needed
            bookRepository.save(new Book("Effective Java", "Joshua Bloch"));
            bookRepository.save(new Book("Clean Code", "Robert C. Martin"));
            bookRepository.save(new Book("Clean Architecture", "Robert C. Martin"));

            List<Book> uncleBobBooks = bookRepository.findByAuthor("Robert C. Martin");
            uncleBobBooks.forEach(b -> System.out.println("By Uncle Bob: " + b.getTitle()));

            // derived query: find by title
            Book cleanCode = bookRepository.findByTitle("Clean Code").orElseThrow(() -> new NoSuchElementException("Clean Code not found"));
            System.out.println("Found: " + cleanCode.getTitle() + " by " + cleanCode.getAuthor());
        };
    }
}
