package com.hwedu.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤web客户端的所有请求，统计请求数据包括客户端Url、IP等
 *
 * @author htcgg
 */
public class WebStatFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //从request中获取请求的相关数据并保存到mongodb
        request.getRequestURI();    //获取客户端url
        request.getRemoteAddr();    //获取客户端IP
        filterChain.doFilter(request, response);
        System.out.println("测试冲突");
    }

}
