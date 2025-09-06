package com.springwiththeo.week13.data_access.dto;

import com.springwiththeo.week13.data_access.model.Author;

public interface BookView {
    String getTitle();//maps to title in book

    String getAuthorId();// maps to author.id in the Book entity

    String getAuthorName(); // maps to author.name in the Book entity

    Author getAuthor();// maps to author in the Book entity and calls the toString() method of the Author class
}
