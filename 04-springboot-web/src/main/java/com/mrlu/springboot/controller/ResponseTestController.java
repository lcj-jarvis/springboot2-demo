package com.mrlu.springboot.controller;

import com.mrlu.springboot.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-15 9:40
 */
@Controller
public class ResponseTestController {

    /**
     * 1、浏览器发请求直接返回xml 【application/xml】  --->有默认的转换器支持转换
     * 2、如果是ajax请求返回json 【application/json】 --->有默认的转换器支持转换
     * 3、如果是硅谷app发请求，返回自定义协议数据。如：【application/x-guigu】 xxxConverter
     *      定义的响应回来的数据为：
     *      属性值1;属性值2
     * 步骤：
     * 1、添加自定义的MessageConverter进系统底层
     * 2、系统底层就会统计出所有MessageConverter能操作哪些类型
     * 3、客户端内容协商[guigu-->guigu]
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/test/person")
    public Person getPerson(){
        Person person = new Person();
        person.setUserName("jack");
        person.setAge(18);
        person.setBirth(new Date());
        return  person;
    }
}
