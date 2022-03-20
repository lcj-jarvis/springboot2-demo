package com.mrlu.springwebflux.controller;

import com.mrlu.springwebflux.bean.User;
import com.mrlu.springwebflux.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-21 10:10
 *
 * 响应式编程：注解方式实现
 *
 * 说明：
 * SpringMVC方式实现，同步阻塞的方式，基于SpringMVC + Servlet + Tomcat
 * SpringWebflux方式实现：异步非阻塞的方式，基于SpringWebflux + Reactor + Netty
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public Mono<User> getUser(@PathVariable("id")Integer id){
        return  userService.getUserById(id);
    }

    @GetMapping("/user")
    public Flux<User> getAllUsers(){
        return  userService.getAllUser();
    }

    /**
     * 使用Postman发起个表单请求测试
     * @param user
     * @return
     */
    @PostMapping("/user")
    public Mono<Void> saveUser(User user){
        log.info("user:{}",user);
        Mono<User> userMono = Mono.just(user);
        return userService.saveUser(userMono);
    }
}
