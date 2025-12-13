package com.example.youxuanshop.entity;

import lombok.Data;

@Data
public class CartItem {
    private Integer goodId;
    private String goodName;
    private Double price;
    private String pictureUrl;
    private Integer quantity;
    
    public Double getTotalPrice() {
        return price * quantity;
    }
}

