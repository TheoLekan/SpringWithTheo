package com.springwiththeo.week16.jpa_advanced_queries.repository;

import com.springwiththeo.week16.jpa_advanced_queries.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
