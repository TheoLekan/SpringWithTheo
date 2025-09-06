package com.springwiththeo.week13.data_access.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book {

    @Getter(onMethod_ = {@Id,@GeneratedValue(strategy = GenerationType.IDENTITY)})
    private Long id;
    private String title;


    @Getter(onMethod_ = @ManyToOne)
    private Author author;

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    // Constructors, getters, and setters
}
