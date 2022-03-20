package com.mrlu.springboot.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-20 19:14
 *
 * 自定义ApplicationContextInitializer
 * 在META-INF/spring.factories文件中加上
 * org.springframework.context.ApplicationContextInitializer=\
 * com.mrlu.springboot.listener.MyApplicationContextInitializer
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("MyApplicationContextInitializer...initialize...");
    }
}
