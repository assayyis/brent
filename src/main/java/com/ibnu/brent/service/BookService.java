package com.ibnu.brent.service;

import com.ibnu.brent.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    List<Book> findByTitle(String title);
    Book findById(String id);

    Book create(Book book);
    Book update(Book book);
    void delete(String id);
}
