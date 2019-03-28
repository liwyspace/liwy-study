package com.liwy.study.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@EnableAutoConfiguration
@SpringBootApplication
@ServletComponentScan // 扫描Servlet
//@MapperScan("com.liwy.study.springboot.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}