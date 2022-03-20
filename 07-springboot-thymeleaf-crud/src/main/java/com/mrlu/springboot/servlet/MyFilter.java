package com.mrlu.springboot.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-18 19:19
 *
 * 自定义Filter方式一：（Servlet3.0的做法）【推荐使用】
 * 1、编写一个类实现Filterj接口
 * 2、在类上使用@WebFilter注解，urlPatterns指定过滤的路径。
 *
 * "/*"是servlet的写法,"/**"是spring的写法
 *
 * 发起http://localhost:8080/css/style.css请求测试
 *
 * 自定义Filter方式二：
 * 在配置类中MyRegisterConfig中使用FilterRegistrationBean
 */
@Slf4j
//@WebFilter(urlPatterns = {"/css/*","/imges/*"})
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       log.info("MyFilter初始化完成...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("MyFilter工作...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("MyFilter销毁...");
    }
}
