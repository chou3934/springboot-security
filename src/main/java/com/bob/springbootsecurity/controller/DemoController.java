package com.bob.springbootsecurity.controller;

import com.bob.springbootsecurity.UserRowMapper;
import com.bob.springbootsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DemoController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @GetMapping("/signIn")
    public String signIn(){
        
        return null;
    }

    @GetMapping("/403")
    public String error403(){
        return "403";
    }



    @PostMapping("/table")
    public String table(Model model){
        String sql="SELECT user_id, user_name, email, password, enabled " +
                "FROM users WHERE 1=1";

        Map<String,Object> map=new HashMap<>();

        List<User> userList=namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
        model.addAttribute("tables",userList);

        return "table";
    }



}
