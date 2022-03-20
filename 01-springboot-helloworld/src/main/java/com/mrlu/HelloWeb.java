package com.mrlu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-10 22:09
 */
@RestController
public class HelloWeb {

    @RequestMapping("/helloWeb")
    public String helloWeb(){
        return "你好";
    }
}
