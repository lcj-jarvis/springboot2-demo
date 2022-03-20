package com.mrlu.springboot.bean;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-11 13:07
 *
 * 配置绑定
 * 读取到springboot的properties文件中的内容，并且把它封装到JavaBean中
 *
 * 第一步：在对应的javabean上加上 @ConfigurationProperties注解，prefix写对应的配置文件的里的key的前缀
 *
 * 第二步：
 *    （1）方式一：在对应的javabean上使用@Component注解加入到容器中
 *               只有在容器中，才有springboot的强大功能
 *     (2)方式二：
 *          在配置类上使用以下注解
 *          @EnableConfigurationProperties(value = {Car.class})
 *          （1）表示开启car配置绑定功能
 *          （2）把Car这个组件加入到容器中
 *
 *       @Data lombok的注解，用于在编译时生setter和getter方法
 *       @ToString lombok的注解,用于生成toString方法
 *       @AllArgsConstructor lombok的注解,用于生成所有参数的有参构造器
 *       @NoArgsConstructor lombok的注解,用于生成无参构造器
 *       @EqualsAndHashCode 重写equals和hashcode方法
 *
 *       如果要定制有参构造器的话，就要显示的写出来
 */
//@Component
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "mycar")
@EqualsAndHashCode
public class Car {

    private String brand;
    private Integer price;

}