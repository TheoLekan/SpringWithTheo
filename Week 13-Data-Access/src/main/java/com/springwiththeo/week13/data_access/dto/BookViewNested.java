package com.springwiththeo.week13.data_access.dto;

public interface BookViewNested {
    String getTitle(); // maps to title in book

    AuthorProjection getAuthor();  // maps to author in the Book entity and calls the AuthorProjection interface
}
