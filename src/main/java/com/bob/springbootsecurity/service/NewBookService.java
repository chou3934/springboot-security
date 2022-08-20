package com.bob.springbootsecurity.service;

import com.bob.springbootsecurity.model.NewBook;
import org.springframework.stereotype.Component;

import java.util.List;

public interface NewBookService {

    List<NewBook> findBooks();

    void saveBook(NewBook entity);

    void deleteBookById(Integer bookId);

    NewBook findBookById(Integer bookId);

    void updateBook(NewBook entity);
}
