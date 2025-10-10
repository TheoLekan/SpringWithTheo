package com.springwiththeo.week17.advanced_jpa.repository;

import com.springwiththeo.week17.advanced_jpa.model.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @EntityGraph(attributePaths = {"books", "books.salesRecords"})
    @Query("SELECT a FROM Author a")
    List<Author> findAllWithBooksAndSales();
}
