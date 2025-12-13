package com.example.youxuanshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("goods")
public class Good {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Double price;
    private String pictureUrl;
    private LocalDateTime releaseTime;
}

