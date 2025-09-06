package com.springwiththeo.week13.data_access.dto;

import com.springwiththeo.week13.data_access.model.Author;

public interface BookView {
    String getTitle();//maps to title in book

    String getAuthorId();// maps to author.id in the Book entity

    String getAuthorName(); // maps to author.name in the Book entity

    Author getAuthor();// maps to author object in the Book entity. If return type is string it will call the ToString Method of the Author Object
}
