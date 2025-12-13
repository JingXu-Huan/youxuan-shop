package com.example.youxuanshop.context;

/**
 * 用户上下文，使用ThreadLocal存储当前登录用户ID
 */
public class UserContext {
    private static final ThreadLocal<Integer> userThreadLocal = new ThreadLocal<>();

    /**
     * 设置当前用户ID
     */
    public static void set(Integer userId) {
        userThreadLocal.set(userId);
    }

    /**
     * 获取当前用户ID
     */
    public static Integer get() {
        return userThreadLocal.get();
    }

    /**
     * 清除当前用户ID（防止内存泄漏）
     */
    public static void remove() {
        userThreadLocal.remove();
    }
}

