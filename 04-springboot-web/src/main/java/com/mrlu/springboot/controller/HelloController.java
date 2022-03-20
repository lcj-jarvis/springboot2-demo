package com.mrlu.springboot.controller;



import org.springframework.web.bind.annotation.*;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-12 12:56
 *
 * @GetMapping 对应于get请求
 * @PostMapping 对应于post请求
 * @PutMapping 对应于put请求
 * @DeleteMapping 对应于delete请求
 *
 */
@RestController
public class HelloController {
//    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @GetMapping(value = "/user")
    public String getUser(){
        return "GET-张三";
    }

   // @RequestMapping(value = "/user",method = RequestMethod.POST)
    @PostMapping(value = "/user")
    public String saveUser(){
        return "POST-张三";
    }

    //@RequestMapping(value = "/user",method = RequestMethod.PUT)
    @PutMapping(value = "/user")
    public String putUser(){
        return "PUT-张三";
    }

    //@RequestMapping(value = "/user",method = RequestMethod.DELETE)
    @DeleteMapping(value = "/user")
    public String deleteUser(){
        return "DELETE-张三";
    }
}
