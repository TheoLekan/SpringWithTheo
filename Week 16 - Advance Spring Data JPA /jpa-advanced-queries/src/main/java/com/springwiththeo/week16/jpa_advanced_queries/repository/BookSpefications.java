package com.springwiththeo.week16.jpa_advanced_queries.repository;

import com.springwiththeo.week16.jpa_advanced_queries.model.Book;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class BookSpefications {

    public static Specification<Book> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"), title);
    }

    public static Specification<Book> hasAuthor(String authorName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.join("author").get("name")), "%" + authorName.toLowerCase() + "%");
    }

    public static Specification<Book> publishedBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("publishedDate"), startDate, endDate);
    }

}
