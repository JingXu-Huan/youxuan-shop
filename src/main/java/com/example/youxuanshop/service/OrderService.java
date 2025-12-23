package com.example.youxuanshop.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.youxuanshop.entity.Order;
import com.example.youxuanshop.entity.OrderGood;
import com.example.youxuanshop.mapper.OrderMapper;
import com.example.youxuanshop.mapper.OrderGoodMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.youxuanshop.entity.CartItem;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderGoodMapper orderGoodMapper;

    @Transactional
    public void createOrder(Order order, List<OrderGood> orderGoods) {
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(false);
        orderMapper.insert(order);

        for (OrderGood orderGood : orderGoods) {
            orderGood.setOrderId(order.getId());
            orderGoodMapper.insert(orderGood);
        }
    }

    public List<Order> findByUserId(Integer userId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.orderByDesc("order_time");
        return orderMapper.selectList(wrapper);
    }

    public List<Order> findAll() {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("status");
        wrapper.orderByDesc("order_time");
        return orderMapper.selectList(wrapper);
    }

    public void deliver(Integer orderId) {
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", orderId);
        wrapper.set("status", true);
        orderMapper.update(null, wrapper);
    }

    public Order findById(Integer id) {
        return orderMapper.selectById(id);
    }

    public List<OrderGood> getOrderGoodsByOrderId(Integer orderId) {
        return orderGoodMapper.findByOrderId(orderId);
    }


    public Map<String, Object> createOrderFromCart(Integer userId, List<CartItem> cart, String address) {
        if (cart == null || cart.isEmpty()) {
            throw new RuntimeException("购物车为空");
        }

        double totalAmount = cart.stream()
                .mapToDouble((item) -> item.getPrice() * item.getQuantity())
                .sum();

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setAddress(address);

        List<OrderGood> orderGoods = cart.stream()
                .map(item -> {
                    OrderGood orderGood = new OrderGood();
                    orderGood.setGoodId(item.getGoodId());
                    orderGood.setPurchaseQuantity(item.getQuantity());
                    return orderGood;
                })
                .collect(Collectors.toList());
        ((OrderService) AopContext.currentProxy()).createOrder(order, orderGoods);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("redirect", "/order.html");
        return result;
    }
}

