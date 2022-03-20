package com.mrlu.springboot.Controller;

import com.mrlu.springboot.bean.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-10 20:02
 *
 * @RestController 相当于 @Controller 和  @ResponseBody的联合使用
 * 然后运行MainApplication的主方法，在浏览器输入http://localhost:8080/hello测试
 *
 * @Slf4j lombok的注解，用于记录日志。加上后，为会该类添加一个log属性
 */
//@Controller
//@ResponseBody

@Slf4j
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello,SpringBoot2!!!你好";
    }

    @Autowired
    private Car car;
    @RequestMapping("/car")
    public Car getCar() {
        log.info("请求进来了。。。");
        return car;
    }
}
