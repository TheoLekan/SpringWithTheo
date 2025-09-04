package com.springwiththeo.week13.data_access.repo;

import com.springwiththeo.week13.data_access.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
