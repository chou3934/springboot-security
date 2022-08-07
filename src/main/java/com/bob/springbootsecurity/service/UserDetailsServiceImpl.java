package com.bob.springbootsecurity.service;

import com.bob.springbootsecurity.model.MyUserDetails;
import com.bob.springbootsecurity.model.User;
import com.bob.springbootsecurity.dto.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

}
