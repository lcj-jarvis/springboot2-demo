package com.mrlu.springboot.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-18 19:11
 *
 * 自定义Servlet方式一（Servlet3.0的做法）【推荐使用】
 * 1、编写一个类继承HttpServlet
 * 2、在该类上使用@WebServlet(urlPatterns = "/my")，urlPatterns指定映射的请求
 * 3、在Springboot的主程序类上加上：
 *  ServletComponentScan(basePackages = {"com.mrlu.springboot"})
 *  表示扫描com.mrlu.springboot包下的所有带有@WebServlet注解的Servlet
 *
 *
 * 发起http://localhost:8080/my请求测试
 * 效果直接响应没有经过spring的拦截器？
 * 原因：DispatchServlet 如何注册进来
 * 	 容器中自动配置了  DispatcherServlet  属性绑定到 WebMvcProperties；
 * 	 对应的配置文件配置项是 spring.mvc。
 * 	 通过 ServletRegistrationBean<DispatcherServlet> 把 DispatcherServlet  配置进来。
 * 	 默认映射的是 / 路径。
 *
 * 	 Tomcat-Servlet；
 * 	 多个Servlet都能处理到同一层路径，精确优选原则
 * 	  A： /my/
 * 	  B： /my/1
 *
 * 自定义Servlet方式二：
 *  在配置类中MyRegisterConfig中使用ServletRegistrationBean
 */
//@WebServlet(urlPatterns = "/my")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.getWriter().println("666666");
    }
}
