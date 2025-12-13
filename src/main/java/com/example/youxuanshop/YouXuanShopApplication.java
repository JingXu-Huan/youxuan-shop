package com.example.youxuanshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.youxuanshop.mapper")
public class YouXuanShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouXuanShopApplication.class, args);
    }

}
