package com.mrlu.springboot.controller;

import com.mrlu.springboot.bean.Student;
import com.mrlu.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-16 20:13
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 发起这个http://localhost:8080/student/1002请求进行测试
     * @param id
     * @return
     */
    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable("id") Integer id){
        return studentService.getStudent(id);
    }
}
