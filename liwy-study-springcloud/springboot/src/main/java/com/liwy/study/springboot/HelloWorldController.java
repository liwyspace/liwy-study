package com.liwy.study.springboot;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/proapi")
    public String test() {
        return "{\"code\":\"200\",\"msg\":\"调用成功!\",\"status\":\"1\",\"responseResult\":{\"status\":false,\"data\":[],\"msg\":\"PMS操作异常\"}}";
    }
}