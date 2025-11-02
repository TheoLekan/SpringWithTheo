package com.springwiththeo.week20.Spring_Hateous;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book{
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    String author;

    public Book( String name, String author) {
        this.name = name;
        this.author = author;
    }
}
