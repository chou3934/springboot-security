package com.bob.springbootsecurity.controller;

import com.bob.springbootsecurity.model.NewBook;
import com.bob.springbootsecurity.rm.GoodsRowMapper;
import com.bob.springbootsecurity.model.Goods;
import com.bob.springbootsecurity.service.NewBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class NewBookController {

    @Autowired
    public NewBookService newBookService;


    @RequestMapping("/newBooks")
    public String doBooksUI(Model model){

        List<NewBook> booksList=newBookService.findBooks();

        model.addAttribute("booksList", booksList);
        return "newBook";
    }

    //新增
    @RequestMapping("/add")
    public String doBooksAddUI(){
        return "books-add";
    }

    @RequestMapping("/doSaveBook")
    public String doSaveBook (NewBook entity) {

        newBookService.saveBook(entity);

        return "redirect:newBooks";
    }

    //刪除
    @RequestMapping("/deleteBook/{bookId}") // rest style URL
    public String doDeleteBookById (@PathVariable Integer bookId) {

        newBookService.deleteBookById(bookId);

        return "redirect:/newBooks";
    }

    //修改
    @RequestMapping("/editBook/{bookId}")
    public String doFinBookById(@PathVariable Integer bookId, Model model){

        NewBook book=newBookService.findBookById(bookId);

        model.addAttribute("b", book);
        return "books-update";
    }

    @PostMapping("/doUpdateBook")
    public String doUpdateBook(NewBook entity){

        newBookService.updateBook(entity);

        return "redirect:newBooks";
    }




    /*

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }



    @PostMapping("/table")
    public String table(Model model){
        String sql="SELECT user_id, user_name, email, password, enabled " +
                "FROM users WHERE 1=1";

        Map<String,Object> map=new HashMap<>();

        List<User> userList=namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
        model.addAttribute("tables",userList);

        return "table";
    }*/



}
