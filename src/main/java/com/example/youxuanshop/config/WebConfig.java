package com.example.youxuanshop.config;

import com.example.youxuanshop.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns(
                        "/cart/**",
                        "/order/**",
                        "/user/getAddress",
                        "/admin/order/**",
                        "/admin/good/add"
                ) // 需要登录的路径
                .excludePathPatterns(
                        "/user/query",
                        "/user/login",
                        "/user/register",
                        "/user/logout",
                        "/user/admin/logout",
                        "/admin/user/logout",
                        "/good/list"
                ); // 不需要登录的路径
    }
}

