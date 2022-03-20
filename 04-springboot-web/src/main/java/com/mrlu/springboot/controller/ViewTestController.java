package com.mrlu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-15 20:01
 */
@Controller
public class ViewTestController {

    /**
     * ThymeleafProperties类的指定了前缀。
     * public static final String DEFAULT_PREFIX = "classpath:/templates/";
     * public static final String DEFAULT_SUFFIX = ".html";
     */
    @GetMapping("/testView")
    public String testView(Model model){
        //model里的数据会被放到request域中
        model.addAttribute("msg","你好，Thymeleaf");
        model.addAttribute("link","http://www.baidu.com");
        return "success";
    }
}
