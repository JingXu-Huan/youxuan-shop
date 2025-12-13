package com.example.youxuanshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("orders_goods")
public class OrderGood {
    private Integer orderId;
    private Integer goodId;
    private Integer purchaseQuantity;
}

