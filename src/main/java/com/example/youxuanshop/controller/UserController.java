package com.example.youxuanshop.controller;

import com.example.youxuanshop.context.UserContext;
import com.example.youxuanshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/query")
    public ResponseEntity<Map<String, Object>> query(HttpSession session) {
        return ResponseEntity.ok(userService.query(session));
    }

    @PostMapping("/user/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String name,
                                                      @RequestParam String password,
                                                      HttpSession session) {
        return ResponseEntity.ok(userService.login(name, password, session));
    }

    @PostMapping("/user/register")
    public ResponseEntity<Map<String, Object>> register(@RequestParam String name,
                                                         @RequestParam String password,
                                                         @RequestParam String address) {
        return ResponseEntity.ok(userService.register(name, password, address));
    }

    @GetMapping("/user/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        return ResponseEntity.ok(userService.logout(session));
    }

    @GetMapping("/admin/user/logout")
    public ResponseEntity<Map<String, Object>> adminLogout(HttpSession session) {
        return ResponseEntity.ok(userService.logout(session));
    }

    @GetMapping("/user/getAddress")
    public ResponseEntity<Map<String, String>> getAddress() {
        Integer userId = UserContext.get();
        Map<String, String> result = new HashMap<>();
        result.put("address", userService.findById(userId).getAddress());
        return ResponseEntity.ok(result);
    }
}
