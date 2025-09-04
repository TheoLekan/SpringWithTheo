package com.springwiththeo.week13.data_access;

import com.springwiththeo.week13.data_access.model.Book;
import com.springwiththeo.week13.data_access.repo.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataAccessProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataAccessProjectApplication.class, args);
	}

    @Bean
    CommandLineRunner init(BookRepository bookRepository) {
        return args -> {
            // Initialize the database with some books if needed
            bookRepository.save(new Book("1984", "George Orwell"));
            bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee"));
            bookRepository.findAll().forEach(book->System.out.print(book.getTitle()+" by "+book.getAuthor()+"\n"));
        };
    }
}
