package com.bob.springbootsecurity.service.implement;

import com.bob.springbootsecurity.dao.NewBookDao;
import com.bob.springbootsecurity.model.NewBook;
import com.bob.springbootsecurity.service.NewBookService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewBookServiceImpl implements NewBookService {

    @Autowired
    public NewBookDao newBookDao;

    @Override
    public List<NewBook> findBooks(){
        List<NewBook> booksList=newBookDao.findBooks();
        return booksList;
    }

    @Override
    public void saveBook(NewBook entity){
        newBookDao.saveBook(entity);
    }

    @Override
    public void deleteBookById(Integer bookId){
        newBookDao.deleteBookById(bookId);
    }

    @Override
    public NewBook findBookById(Integer bookId) {
        return newBookDao.findBookById(bookId);
    }

    @Override
    public void updateBook(NewBook entity) {
        newBookDao.updateBook(entity);
    }
}
