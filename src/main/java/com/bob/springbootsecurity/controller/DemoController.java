package com.bob.springbootsecurity.controller;

import com.bob.springbootsecurity.model.User;
import com.bob.springbootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DemoController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() { return "index"; }

    @RequestMapping("/createUser")
    public String doUSerAddUI(){return "users-add";}

    @RequestMapping("/doSaveUser")
    public String doSaveUser(User user){

        userService.saveUser(user);

        return "redirect:/";
    }

    @GetMapping("/403")
    public String error403(){ return "403"; }

}

