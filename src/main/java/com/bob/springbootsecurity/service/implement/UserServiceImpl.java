package com.bob.springbootsecurity.service.implement;

import com.bob.springbootsecurity.dao.UserDao;
import com.bob.springbootsecurity.model.User;
import com.bob.springbootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    @Override
    public void saveUser(User user) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String rawPassword=user.getPassword();
        String encodedPassword=encoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        System.out.println(user.getPassword());

        userDao.saveUser(user);
    }

}
