package com.bob.springbootsecurity.rm;

import com.bob.springbootsecurity.model.Goods;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsRowMapper implements RowMapper<Goods> {

    @Override
    public Goods mapRow(ResultSet rs, int rowNum) throws SQLException {
        Goods goods=new Goods();
        goods.setId(rs.getLong("id"));
        goods.setName(rs.getString("name"));
        goods.setRemark(rs.getString("remark"));
        goods.setCreatedTime(rs.getTime("createdTime"));

        return goods;
    }
}
