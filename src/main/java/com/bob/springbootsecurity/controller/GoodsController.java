package com.bob.springbootsecurity.controller;

import com.bob.springbootsecurity.model.Goods;
import com.bob.springbootsecurity.rm.GoodsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
}
