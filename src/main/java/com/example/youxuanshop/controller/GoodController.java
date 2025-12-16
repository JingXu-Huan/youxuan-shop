package com.example.youxuanshop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.youxuanshop.entity.Good;
import com.example.youxuanshop.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;

    @GetMapping("/good/list")
    public ResponseEntity<List<Good>> list() {
        List<Good> goods = goodService.findAll();
        return ResponseEntity.ok(goods);
    }

    @GetMapping("/good/page")
    public ResponseEntity<Map<String, Object>> page(@RequestParam(defaultValue = "1") Integer current,
                                                     @RequestParam(defaultValue = "5") Integer size) {
        IPage<Good> page = goodService.findPage(current, size);
        Map<String, Object> result = new HashMap<>();
        result.put("records", page.getRecords());
        result.put("total", page.getTotal());
        result.put("current", page.getCurrent());
        result.put("size", page.getSize());
        result.put("pages", page.getPages());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/admin/good/add")
    public ResponseEntity<Map<String, Object>> add(@RequestParam String name,
                                                   @RequestParam Double price,
                                                   @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<>();
        Good good = new Good();
        good.setName(name);
        good.setPrice(price);
        goodService.addGood(good, file);
        result.put("success", true);
        result.put("redirect", "/admin/good.html");
        return ResponseEntity.ok(result);
    }
}

