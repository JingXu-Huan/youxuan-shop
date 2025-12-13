package com.example.youxuanshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.youxuanshop.entity.OrderGood;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderGoodMapper extends BaseMapper<OrderGood> {
    default List<OrderGood> findByOrderId(Integer orderId) {
        QueryWrapper<OrderGood> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", orderId);
        return selectList(wrapper);
    }
}

