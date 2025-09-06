package com.springwiththeo.week13.data_access.repo;

import com.springwiththeo.week13.data_access.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    Optional<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    List<Book> searchByTitle(@Param("title") String title);

    @Query("SELECT b.title FROM Book b WHERE b.author.name = :author")
    List<String> findTitlesByAuthor(@Param("author") String author);

}
