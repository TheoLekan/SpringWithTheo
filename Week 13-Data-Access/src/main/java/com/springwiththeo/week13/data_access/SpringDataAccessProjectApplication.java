package com.springwiththeo.week13.data_access;

import com.springwiththeo.week13.data_access.dto.BookSummary;
import com.springwiththeo.week13.data_access.model.Author;
import com.springwiththeo.week13.data_access.model.Book;
import com.springwiththeo.week13.data_access.repo.AuthorRepository;
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
    CommandLineRunner init(BookRepository bookRepository, AuthorRepository authorRepo) {
        return args -> {
            Author uncleBob = authorRepo.save(new Author("Robert C. Martin"));
            Author bloch = authorRepo.save(new Author("Joshua Bloch"));
            // Initialize the database with some books if needed
            bookRepository.save(new Book("Effective Java", uncleBob));
            bookRepository.save(new Book("Clean Code", uncleBob));
            bookRepository.save(new Book("Clean Architecture", bloch));

            // derived query: find by title
            Book cleanCode = bookRepository.findByTitle("Clean Code").orElseThrow(() -> new NoSuchElementException("Clean Code not found"));
            System.out.println("Found: " + cleanCode.getTitle() + " by " + cleanCode.getAuthor().getName());

            // JPQL search
            List<Book> cleanBooks = bookRepository.searchByTitle("Clean");
            cleanBooks.forEach(b -> System.out.println("Found with JPQL: " + b.getTitle()));

            //Scalar Projection (Single Column)
            List<String> titlesOnlyByAuthor = bookRepository.findTitlesByAuthor("Robert C. Martin");
            System.out.println("\n-------\nScalar Projection (Single Column) of Books by Robert C. Martin:");
            titlesOnlyByAuthor.forEach(System.out::println);

            //Projection using DTO (Multiple Columns)
            List<BookSummary> bookSummariesByAuthor = bookRepository.findBookSummariesByAuthor("Robert C. Martin");
            System.out.println("\n-------\nProjection using DTO (Multiple Columns) of Books by Robert C. Martin:");
            bookSummariesByAuthor.forEach(bs -> System.out.println(bs.title() + " by " + bs.authorName()));

        };
    }
}
