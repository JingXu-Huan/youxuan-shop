package com.example.youxuanshop.interceptor;

import com.example.youxuanshop.context.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录拦截器
 * 检查Session中的userId，如果已登录则将userId存储到ThreadLocal中
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(401);
            response.getWriter().write("请先登录");
            return false;
        }

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.setStatus(401);
            response.getWriter().write("请先登录");
            return false;
        }
        // 保存用户 ID 到 ThreadLocal
        UserContext.set(userId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContext.remove();
    }
}

