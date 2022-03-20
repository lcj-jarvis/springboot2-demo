package com.mrlu.springboot.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-20 19:15
 *
 * 自定义ApplicationListener
 * 在META-INF/spring.factories文件中加上
 * org.springframework.context.ApplicationListener=\
 * com.mrlu.springboot.listener.MyApplicationListener
 */
public class MyApplicationListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("MyApplicationListener...onApplicationEvent...");
    }
}
