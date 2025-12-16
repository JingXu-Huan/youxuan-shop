package com.example.youxuanshop.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("orders_goods")
public class OrderGood {
    @TableField("order_id")
    private Integer orderId;
    @TableField("good_id")
    private Integer goodId;
    @TableField("purchase_quantity")
    private Integer purchaseQuantity;
}

