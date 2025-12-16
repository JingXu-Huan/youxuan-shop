package com.example.youxuanshop.controller;

import com.example.youxuanshop.entity.CartItem;
import com.example.youxuanshop.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart/add")
    public ResponseEntity<Map<String, Object>> add(@RequestParam Integer goodId,
                                                   @RequestParam(required = false) String goodName,
                                                   @RequestParam(defaultValue = "1") Integer quantity,
                                                   HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        try {
            int cartCount = cartService.addToCart(goodId, quantity, session);
            result.put("success", true);
            result.put("cartCount", cartCount);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/cart/list")
    public ResponseEntity<List<CartItem>> list(HttpSession session) {
        return ResponseEntity.ok(cartService.getCartList(session));
    }
}
