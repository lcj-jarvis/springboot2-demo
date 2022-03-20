package com.mrlu.springboot.Controller;

import com.mrlu.springboot.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-11 17:25
 */
@Slf4j
@RestController
public class HelloPerson {

    @Autowired
    private Person person;
    @RequestMapping("/person")
    public Person getPerson(){
        log.info("请求进来了。。。。");
        log.info(person.getUserName());
        return person;
    }
}
