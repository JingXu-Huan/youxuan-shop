package com.example.youxuanshop.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.youxuanshop.entity.User;
import com.example.youxuanshop.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final CartService cartService;

    public User findByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return userMapper.selectOne(wrapper);
    }

    public void register(User user) {
        user.setIsAdmin(false);
        userMapper.insert(user);
    }

    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    public Map<String, Object> login(String name, String password, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = findByName(name);
        
        if (user != null && user.getPassword().equals(password)) {
            // 将userId存储到Session中
            session.setAttribute("userId", user.getId());
            
            result.put("success", true);
            result.put("userId", user.getId());
            result.put("isAdmin", user.getIsAdmin());
            if (user.getIsAdmin()) {
                result.put("redirect", "/admin/order.html");
            } else {
                result.put("redirect", "/index.html");
            }
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
        }
        return result;
    }

    public Map<String, Object> register(String name, String password, String address) {
        Map<String, Object> result = new HashMap<>();
        
        User existingUser = findByName(name);
        if (existingUser != null) {
            result.put("success", false);
            result.put("message", "用户名已被注册");
            return result;
        }
        
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setAddress(address);
        user.setIsAdmin(false);
        userMapper.insert(user);
        
        result.put("success", true);
        result.put("redirect", "/login.html");
        return result;
    }

    public Map<String, Object> query(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId != null) {
            User user = findById(userId);
            result.put("loggedIn", true);
            result.put("userName", user.getName());
            result.put("cartCount", cartService.getCartCount(session));
        } else {
            result.put("loggedIn", false);
        }
        
        return result;
    }

    public Map<String, Object> logout(HttpSession session) {
        session.invalidate();
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("redirect", "/index.html");
        return result;
    }
}

