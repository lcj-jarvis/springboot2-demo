package com.mrlu.springboot.Controller;

import com.mrlu.springboot.bean.Manager;
import com.mrlu.springboot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-19 17:18
 */
@RestController
public class HelloController {

    /**
     * person.name的值默认是李四
     */
    @Value("${person.name:李四}")
    private String name;

    @GetMapping("/")
    public String hello(){
        return "Hello " + name;
    }

    @Autowired
    private Person person;

    /**
     * 发起不同测试环境下的person请求来测试
     * @return
     */
    @GetMapping("/person")
    public String getPerson(){
       return person.getClass().toString();
    }

    @GetMapping("/group/person")
    public Person getPersonByGroup(){
        return person;
    }

    @Value("${MAVEN_HOME}")
    private String mavenPath;

    /**
     * 从系统的环境变量中获取值
     */
    @GetMapping("/maven")
    public String getMavenPath(){
        return mavenPath;
    }

    @Autowired
    private Manager manager;
    /**
     *
     * http://localhost:7000/manager
     * 测试不同位置的配置文件
     * @return
     */
    @GetMapping("/manager")
    public Manager getManager(){
        return manager;
    }

}
