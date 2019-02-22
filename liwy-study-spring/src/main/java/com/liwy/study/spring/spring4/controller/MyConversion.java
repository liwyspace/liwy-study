package com.liwy.study.spring.spring4.controller;

import com.liwy.study.spring.spring4.bean.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 自定义类型转换器
 * Created by liwy on 2017/4/4.
 */
@Component
public class MyConversion implements Converter<String,User> {
    @Override
    public User convert(String s) {
        //username:email
        if(s != null){
            String [] vals = s.split(":");
            if(vals != null && vals.length == 2){
                String username = vals[0];
                String email = vals[1];
                User user = new User();
                user.setUsername(username);
                user.setEmail(email);
                return user;
            }
        }
        return null;
    }
}