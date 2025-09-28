package com.springwiththeo.week16.jpa_advanced_queries.service;

import com.springwiththeo.week16.jpa_advanced_queries.model.Author;
import com.springwiththeo.week16.jpa_advanced_queries.model.Book;
import com.springwiththeo.week16.jpa_advanced_queries.repository.BookRepository;
import com.springwiththeo.week16.jpa_advanced_queries.repository.BookSpefications;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    @Bean
    public CommandLineRunner sampleMethod(){
        return args -> {
            Author author = new Author("J.K. Rowling", "British");
            Author author2 = new Author("George R.R. Martin", "American");

            Book book1 = new Book("Harry Potter and the Philosopher's Stone", "Fantasy", LocalDate.of(1997, 6, 26), author);
            Book book2 = new Book("A Game of Thrones", "Fantasy", LocalDate.of(1996, 8, 6), author2);
            Book book3 = new Book("Harry Potter and the Chamber of Secrets", "Fantasy", LocalDate.of(1998, 7, 2), author);
            Book book4 = new Book("A Clash of Kings", "Fantasy", LocalDate.of(1998, 11, 16), author2);

            bookRepository.saveAll(List.of(book1, book2, book3, book4));
            bookRepository.findAll().forEach(System.out::println);

            bookRepository.findAll(BookSpefications.hasTitle("A Game of Thrones")).forEach(System.out::println);

            bookRepository.findAll(BookSpefications.publishedBetween(LocalDate.of(1998,11,15),LocalDate.of(2000,11,15)))
                    .forEach(System.out::println);


            Specification<Book> combinedSpec = Specification.allOf(
                    BookSpefications.hasAuthor("Rowling"),
                    BookSpefications.publishedBetween(
                            LocalDate.of(1997,1,1),
                            LocalDate.of(1999,1,1)
                    )
            );

            System.out.print("Books by Rowling published between 1997 and 1999: ");
            List<Book> all = bookRepository.findAll(combinedSpec);
            all.forEach(System.out::println);
        };
    }


}
