package com.bob.springbootsecurity.controller;

import com.bob.springbootsecurity.model.NewBook;
import com.bob.springbootsecurity.rm.GoodsRowMapper;
import com.bob.springbootsecurity.model.Goods;
import com.bob.springbootsecurity.rm.NewBookRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DemoController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    //新書頁面
    @RequestMapping("/newBook")
    public String doBooksUI(Model model){

        String sql="SELECT * FROM newbook WHERE 1=1";

        Map<String,Object> map=new HashMap<>();


        List<NewBook> booksList=namedParameterJdbcTemplate.query(sql,map,new NewBookRowMapper());
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

        String sql="INSERT INTO newbook(title, author, call_number, " +
                "barcode, exh_fr, exh_end, act_yn) " +
                "VALUES ( :title, :author, :callNumber, " +
                ":barcode, :exhFr, :exhEnd, :actYn)";

        Map<String,Object> map=new HashMap<>();
        map.put("title",entity.getTitle());
        map.put("author",entity.getAuthor());
        map.put("callNumber",entity.getCallNumber());
        map.put("barcode",entity.getBarcode());
        map.put("exhFr",entity.getExhFr());
        map.put("exhEnd",entity.getExhEnd());
        map.put("actYn",0);


        KeyHolder keyHolder=new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        return "redirect:newBook";
    }

    //刪除
    @RequestMapping("/deleteBook/{bookId}") // rest style URL
    public String doDeleteBookById (@PathVariable Integer bookId) {
        String sql="DELETE FROM newbook WHERE book_id= :bookId";

        Map<String,Object> map=new HashMap<>();
        map.put("bookId",bookId);

        namedParameterJdbcTemplate.update(sql,map);

        return "redirect:/newBook";
    }

    //修改
    @RequestMapping("/editBook/{bookId}")
    public String doFinBookById(@PathVariable Integer bookId, Model model){
        String sql="SELECT * FROM newbook WHERE book_id= :bookId";

        Map<String,Object> map=new HashMap<>();
        map.put("bookId",bookId);

        List<NewBook> booksList=namedParameterJdbcTemplate.query(sql,map,new NewBookRowMapper());
        NewBook book=new NewBook();
        book.setBookId(booksList.get(0).getBookId());
        book.setTitle(booksList.get(0).getTitle());
        book.setAuthor(booksList.get(0).getAuthor());
        book.setCallNumber(booksList.get(0).getCallNumber());
        book.setBarcode(booksList.get(0).getBarcode());
        book.setExhEnd(booksList.get(0).getExhFr());
        book.setExhFr(booksList.get(0).getExhEnd());
        book.setReviseDate(booksList.get(0).getReviseDate());
        book.setActYn(booksList.get(0).getActYn());

        model.addAttribute("b", book);
        return "books-update";
    }

    @PostMapping("/doUpdateBook")
    public String doUpdateBook(NewBook entity){
        String sql="UPDATE newbook " +
                "SET title= :title, author= :author, call_number= :callNumber, barcode= :barcode, " +
                "exh_fr= :exhFr, exh_end= :exhEnd, act_yn= :actYn " +
                "WHERE book_id= :bookId";

        Map<String, Object> map=new HashMap<>();
        map.put("bookId",entity.getBookId());
        map.put("title",entity.getTitle());
        map.put("author",entity.getAuthor());
        map.put("callNumber",entity.getCallNumber());
        map.put("barcode",entity.getBarcode());
        map.put("exhFr",entity.getExhFr());
        map.put("exhEnd",entity.getExhEnd());
        map.put("actYn",entity.getActYn());

        namedParameterJdbcTemplate.update(sql,map);

        return "redirect:newBook";
    }




    //goods頁面
    @RequestMapping("/goods")
    public String doGoodsUI(Model model){

        String sql="SELECT * FROM goods WHERE 1=1";

        Map<String,Object> map=new HashMap<>();


        List<Goods> goodsList=namedParameterJdbcTemplate.query(sql,map,new GoodsRowMapper());
        model.addAttribute("goodsList", goodsList);
        return "goods";}


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

        return "redirect:goods";
    }


    //刪除
    @RequestMapping("/delete/{id}") // rest style URL
    public String doDeleteById (@PathVariable Integer id) {
        String sql="DELETE FROM goods WHERE id= :id";

        Map<String,Object> map=new HashMap<>();
        map.put("id",id);

        namedParameterJdbcTemplate.update(sql,map);

        return "redirect:goods";
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

        return "redirect:goods";
    }


    @GetMapping("/403")
    public String error403(){
        return "403";
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
