package com.example.youxuanshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.youxuanshop.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}

