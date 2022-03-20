package com.mrlu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-11 20:32
 */
//@RestController
@Controller
public class HelloStatic {

    @RequestMapping("/2.jpg")
    @ResponseBody
    public String hello(){
        return "aaaaa";
    }

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }
}
