package com.example.youxuanshop.service;

import com.example.youxuanshop.entity.CartItem;
import com.example.youxuanshop.entity.Good;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final GoodService goodService;

    public int addToCart(Integer goodId, Integer quantity, HttpSession session) {
        List<CartItem> cart = getCart(session);
        
        Good good = goodService.findById(goodId);
        if (good == null) {
            throw new RuntimeException("商品不存在");
        }
        boolean found = false;
        for (CartItem item : cart) {
            if (item.getGoodId().equals(goodId)) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }
        if (!found) {
            CartItem cartItem = new CartItem();
            cartItem.setGoodId(good.getId());
            cartItem.setGoodName(good.getName());
            cartItem.setPrice(good.getPrice());
            cartItem.setPictureUrl(good.getPictureUrl());
            cartItem.setQuantity(quantity);
            cart.add(cartItem);
        }
        return cart.stream().mapToInt(CartItem::getQuantity).sum();
    }

    public List<CartItem> getCartList(HttpSession session) {
        return getCart(session);
    }

    public int getCartCount(HttpSession session) {
        List<CartItem> cart = getCart(session);
        return cart.stream().mapToInt(CartItem::getQuantity).sum();
    }

    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}

