package com.example.youxuanshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.youxuanshop.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

