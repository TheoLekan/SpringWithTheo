package com.springwiththeo.week13.data_access.repo;

import com.springwiththeo.week13.data_access.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Additional query methods can be defined here if needed
    List<Book> findByAuthor(String author);

    Optional<Book> findByTitle(String title);

}
