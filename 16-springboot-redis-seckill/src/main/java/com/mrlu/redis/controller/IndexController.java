package com.mrlu.redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 简单de快乐
 * @date 2021-07-25 22:11
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String toIndex() {
        return "index";
    }



}
