package com.example.youxuanshop.controller;

import com.example.youxuanshop.context.UserContext;
import com.example.youxuanshop.entity.CartItem;
import com.example.youxuanshop.entity.Good;
import com.example.youxuanshop.entity.Order;
import com.example.youxuanshop.entity.OrderGood;
import com.example.youxuanshop.service.GoodService;
import com.example.youxuanshop.service.OrderService;
import com.example.youxuanshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final GoodService goodService;

    @PostMapping("/order/add")
    public ResponseEntity<Map<String, Object>> add(HttpSession session) {
        Integer userId = UserContext.get();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        String address = userService.findById(userId).getAddress();
        
        try {
            Map<String, Object> result = orderService
                    .createOrderFromCart(userId, cart, address);
            session.removeAttribute("cart");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", e.getMessage());
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/order/list")
    public ResponseEntity<List<Map<String, Object>>> list() {
        Integer userId = UserContext.get();
        List<Order> orders = orderService.findByUserId(userId);
        return ResponseEntity.ok(buildOrderList(orders));
    }

    @GetMapping("/admin/order/list")
    public ResponseEntity<List<Map<String, Object>>> adminList() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(buildOrderList(orders));
    }

    @PostMapping("/admin/order/deliver")
    public ResponseEntity<Map<String, Object>> deliver(@RequestParam Integer orderId) {
        orderService.deliver(orderId);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return ResponseEntity.ok(result);
    }

    private List<Map<String, Object>> buildOrderList(List<Order> orders) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Order order : orders) {
            Map<String, Object> orderMap = new HashMap<>();
            orderMap.put("id", order.getId());
            orderMap.put("orderTime", order.getOrderTime());
            orderMap.put("totalAmount", order.getTotalAmount());
            orderMap.put("status", order.getStatus());
            orderMap.put("address", order.getAddress());
            
            if (order.getUserId() != null) {
                var orderUser = userService.findById(order.getUserId());
                orderMap.put("userName", orderUser != null ? orderUser.getName() : "");
            }
            
            List<Map<String, Object>> goods = new ArrayList<>();
            List<OrderGood> orderGoods = orderService.getOrderGoodsByOrderId(order.getId());
            for (OrderGood orderGood : orderGoods) {
                Good good = goodService.findById(orderGood.getGoodId());
                Map<String, Object> goodMap = new HashMap<>();
                goodMap.put("name", good.getName());
                goodMap.put("price", good.getPrice());
                goodMap.put("quantity", orderGood.getPurchaseQuantity());
                goods.add(goodMap);
            }
            orderMap.put("goods", goods);
            result.add(orderMap);
        }
        return result;
    }
}
