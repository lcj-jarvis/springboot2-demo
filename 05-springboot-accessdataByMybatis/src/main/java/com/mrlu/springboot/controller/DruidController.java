package com.mrlu.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-16 16:54
 */
@RestController
public class DruidController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/sql")
    public String queryFromDb(){
        Long count = jdbcTemplate.queryForObject("select count(*) from student", Long.class);
        return count.toString();
    }
}
