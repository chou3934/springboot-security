package com.bob.springbootsecurity.controller;

import com.bob.springbootsecurity.GoodsRowMapper;
import com.bob.springbootsecurity.UserRowMapper;
import com.bob.springbootsecurity.model.Goods;
import com.bob.springbootsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;
import java.lang.reflect.Parameter;
import java.util.*;

@Controller
public class DemoController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @RequestMapping("/")
    public String doGoodsUI(Model model){

        String sql="SELECT * FROM goods WHERE 1=1";

        Map<String,Object> map=new HashMap<>();


        List<Goods> goodsList=namedParameterJdbcTemplate.query(sql,map,new GoodsRowMapper());
        model.addAttribute("goodsList", goodsList);
        return "index";}


    //新增
    @RequestMapping("/new")
    public String doGoodsAddUI(){
        return "goods-adds";
    }

    @RequestMapping("/doSaveGoods")
    public String doSaveGoods (Goods entity) {

        String sql="INSERT INTO goods(name, remark, createdTime) " +
                "VALUES ( :name, :remark, :createdTime)";

        Map<String,Object> map=new HashMap<>();
        map.put("name",entity.getName());
        map.put("remark",entity.getRemark());

        Date now=new Date();
        map.put("createdTime",now);

        KeyHolder keyHolder=new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        return "redirect:/";
    }


    //刪除
    @RequestMapping("/delete/{id}") // rest style URL
    public String doDeleteById (@PathVariable Integer id) {
        String sql="DELETE FROM goods WHERE id= :id";

        Map<String,Object> map=new HashMap<>();
        map.put("id",id);

        namedParameterJdbcTemplate.update(sql,map);

        return "redirect:/";
    }


    //修改
    @RequestMapping("/edit/{id}")
    public String doFinById(@PathVariable Integer id, Model model){
        String sql="SELECT * FROM goods WHERE id= :id";

        Map<String,Object> map=new HashMap<>();
        map.put("id",id);

        List<Goods> goodList=namedParameterJdbcTemplate.query(sql,map,new GoodsRowMapper());
        Goods goods=new Goods();
        goods.setId(goodList.get(0).getId());
        goods.setName(goodList.get(0).getName());
        goods.setRemark(goodList.get(0).getRemark());
        goods.setCreatedTime(goodList.get(0).getCreatedTime());

        model.addAttribute("g", goods);
        return "goods-update";
    }

    @PostMapping("/doUpdateGoods")
    public String doUpdateGoods(Goods entity){
        String sql="UPDATE goods " +
                "SET name= :name, remark= :remark, createdTime= :createdTime " +
                "WHERE id= :id";

        Map<String, Object> map=new HashMap<>();
        map.put("id",entity.getId());
        map.put("name",entity.getName());
        map.put("remark",entity.getRemark());

        Date now=new Date();
        map.put("createdTime",now);

        namedParameterJdbcTemplate.update(sql,map);

        return "redirect:/";
    }


    @GetMapping("/403")
    public String error403(){
        return "403";
    }





    /*@RequestMapping(value = "/", method = RequestMethod.GET)
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
