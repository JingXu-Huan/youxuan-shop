package com.example.youxuanshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.example.youxuanshop.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class YouXuanShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouXuanShopApplication.class, args);
    }

}
