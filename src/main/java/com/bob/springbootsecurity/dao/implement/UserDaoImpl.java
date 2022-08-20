package com.bob.springbootsecurity.dao.implement;

import com.bob.springbootsecurity.dao.UserDao;
import com.bob.springbootsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void saveUser(User user) {
        String sql="INSERT INTO users(user_name, email, password, enabled) " +
                "VALUES ( :userName, :email, :password, :enabled)";

        Map<String,Object> map=new HashMap<>();
        map.put("userName",user.getUserName());
        map.put("email",user.getEmail());
        map.put("password",user.getPassword());
        map.put("enabled",1);

        KeyHolder keyHolder=new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
    }
}
