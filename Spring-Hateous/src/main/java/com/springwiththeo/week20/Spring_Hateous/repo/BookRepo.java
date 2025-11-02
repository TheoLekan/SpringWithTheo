package com.springwiththeo.week20.Spring_Hateous.repo;

import com.springwiththeo.week20.Spring_Hateous.Book;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface BookRepo extends JpaRepository<Book, Long> {
}
