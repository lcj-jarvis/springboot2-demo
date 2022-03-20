package com.mrlu.springboot.service;

import com.mrlu.springboot.bean.Student;
import com.mrlu.springboot.dao.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-16 20:14
 */
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public Student getStudent(Integer id){
        return studentMapper.getStudent(id);
    }
}
