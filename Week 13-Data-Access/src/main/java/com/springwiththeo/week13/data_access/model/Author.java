package com.springwiththeo.week13.data_access.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString(exclude = "books")
@NoArgsConstructor
public class Author {

    @Getter(onMethod_ = {@Id, @GeneratedValue(strategy = GenerationType.IDENTITY)})
    private Long id;
    private String name;

    public Author(String name) {
        this.name = name;
    }

    @Getter(onMethod_ = @OneToMany(mappedBy = "author"))
    List<Book> books;
}
