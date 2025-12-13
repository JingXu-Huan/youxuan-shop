package com.example.youxuanshop.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.youxuanshop.entity.Good;
import com.example.youxuanshop.mapper.GoodMapper;
import com.example.youxuanshop.oss.OssUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoodService {
    private final GoodMapper goodMapper;
    private final OssUtil ossUtil;

    public List<Good> findAll() {
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("release_time");
        return goodMapper.selectList(wrapper);
    }

    public IPage<Good> findPage(Integer current, Integer size) {
        Page<Good> page = new Page<>(current, size);
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("release_time");
        return goodMapper.selectPage(page, wrapper);
    }

    public Good findById(Integer id) {
        return goodMapper.selectById(id);
    }

    public void addGood(Good good, MultipartFile file) throws IOException {
        good.setReleaseTime(LocalDateTime.now());
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : "";
        String filename = "image/" + UUID.randomUUID() + suffix;
        InputStream inputStream = file.getInputStream();
        String upload = ossUtil.upload(inputStream, filename);
        good.setPictureUrl(upload);
        goodMapper.insert(good);
    }
}

