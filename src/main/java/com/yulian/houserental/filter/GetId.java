package com.yulian.houserental.filter;

import com.yulian.houserental.utils.JwtUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetId extends HandlerInterceptorAdapter {

    public static Integer id = null;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String s = request.getHeader("Authorization");
        id = JwtUtils.getMemberIdByJwtToken(s);
        return true;
    }
}
