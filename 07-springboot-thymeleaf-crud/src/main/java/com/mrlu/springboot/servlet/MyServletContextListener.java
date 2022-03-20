package com.mrlu.springboot.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-18 19:16
 *
 * 自定义ServletContextListener监听器方式一（Servlet3.0的做法）【推荐使用】
 * 1、编写一个类实现ServletContextListener接口
 * 2、在类的上面使用@WebListener注解
 *
 *  自定义ServletContextListener方式二：
 *  在配置类中MyRegisterConfig中使用ServletListenerRegistrationBean
 */
@Slf4j
//@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
       log.info("MyServletContextListener监听到项目初始化完成。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("MyServletContextListener监听到项目销毁。。。");
    }
}
