package com.kenny.gscaching;

public interface BookRepository {

    Book getByIsbn(String isbn);
}
