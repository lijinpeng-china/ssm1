package com.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//也可以不注入bean，使用工具类，当前service使用的是bean的方式
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder byBCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return byBCryptPasswordEncoder.encode(password);
    }
    public static void main(String[] args){
        String password = "123";
        String s = BCryptPasswordEncoderUtils.encodePassword(password);
        //$2a$10$Kzzfayl1lXy9F3VOiuh6FebJild7iQoP3gDqmG4VTn7nxvR.ubNq2
        //每次加密结果都不同
        System.out.println(s);
    }
}
