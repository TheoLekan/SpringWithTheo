package com.springwiththeo.week16.jpa_advanced_queries.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;
    String genre;
    LocalDate publishedDate;

    public Book(String title, String genre, LocalDate publishedDate, Author author) {
        this.title = title;
        this.genre = genre;
        this.publishedDate = publishedDate;
        this.author = author;
    }

    @ManyToOne(optional = false ,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    Author author;
}
