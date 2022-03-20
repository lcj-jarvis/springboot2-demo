package com.mrlu.springboot.controller;

import com.mrlu.springboot.actuator.metrics.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-19 15:45
 */
@RestController
public class MetricsTestController {
    @Autowired
    private MyService myService;

    @GetMapping("/testCountRequest")
    public String Hello(){
        return myService.hello();
    }
}
