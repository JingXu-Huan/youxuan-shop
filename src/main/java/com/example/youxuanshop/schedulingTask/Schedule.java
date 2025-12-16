package com.example.youxuanshop.schedulingTask;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author jingxu
 * @version 1.0.0
 * @since 2025/12/14
 */
@Component
@RequiredArgsConstructor
public class Schedule {

    private final StringRedisTemplate redisTemplate;
    //10毫秒执行一次定时任务
    @Scheduled(fixedRate = 10)
    public void task() {
        String s = String.valueOf(new Random().nextInt());
        redisTemplate.opsForValue().set(s,s,10, TimeUnit.SECONDS);
    }
}
