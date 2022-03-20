package com.mrlu.springboot.controller;

import com.mrlu.springboot.bean.City;
import com.mrlu.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-16 20:56
 */
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * http://localhost:8080/city/1
     * @param id
     * @return
     */
    @GetMapping("/city/{id}")
    public City getCity(@PathVariable("id") Integer id){
         return cityService.getCity(id);
    }

    /**
     * 使用Postman发送表单Post请求
     * @param city
     * @return
     */
    @PostMapping("/city")
    public City saveCity(City city){
        cityService.saveCity(city);
        System.out.println("插入后对应的主键："+city.getId());
        return city;
    }
}
