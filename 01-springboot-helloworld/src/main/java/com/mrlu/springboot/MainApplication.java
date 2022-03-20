package com.mrlu.springboot;

import com.mrlu.springboot.bean.Pet;
import com.mrlu.springboot.bean.User;
import com.mrlu.springboot.config.MyConfig01;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.Date;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-10 19:56
 *
 * 主程序类
 * @SpringBootApplication 这是一个SpringBoot应用
 *
 * @SpringBootApplication注解相当于
 * 下面这三个注解一起使用
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
 *         @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
 */
//@SpringBootApplication(scanBasePackages = "com.mrlu")

/*
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
*/
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        //返回ioc容器
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        String[] names = context.getBeanDefinitionNames();
        for (String name:names) {
            System.out.println(name);
        }
        MyConfig01 myConfig = context.getBean("myConfig", MyConfig01.class);
        User user1 = myConfig.user();
        User user = context.getBean("user", User.class);
        Pet pet = user.getPet();
        Pet tom = context.getBean("tom", Pet.class);
        System.out.println("config===>"+myConfig);
        System.out.println("user===>"+(user==user1));
        System.out.println("pet===>"+(pet==tom));
        System.out.println("==============================");
        Date date = context.getBean("java.util.Date", Date.class);
        System.out.println(date);
        String[] namesForType = context.getBeanNamesForType(User.class);
        for (String name : namesForType) {
            System.out.println(name);
        }

        System.out.println(context.containsBean("user01"));
        System.out.println(context.containsBean("haha"));
        System.out.println(context.containsBean("hehe"));

        System.out.println(context.getBeanDefinitionCount());
    }
}
