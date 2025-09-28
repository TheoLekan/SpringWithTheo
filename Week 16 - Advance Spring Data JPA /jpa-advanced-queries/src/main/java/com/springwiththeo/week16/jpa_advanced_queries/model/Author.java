package com.springwiththeo.week16.jpa_advanced_queries.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Author {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String nationality;

    public Author(String name, String nationality) {
        this.nationality = nationality;
        this.name = name;
    }
}
