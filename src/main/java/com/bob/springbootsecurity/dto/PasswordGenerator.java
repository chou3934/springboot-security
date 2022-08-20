package com.bob.springbootsecurity.dto;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String rawPassword="editor";
        String encodedPassword=encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}
