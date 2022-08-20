package com.bob.springbootsecurity.dao;

import com.bob.springbootsecurity.model.NewBook;

import java.util.List;

public interface NewBookDao {

    List<NewBook> findBooks();

    void saveBook(NewBook entity);

    void deleteBookById(Integer bookId);

    NewBook findBookById(Integer bookId);

    void updateBook(NewBook entity);
}
