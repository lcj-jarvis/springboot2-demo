package com.mrlu.springboot.dao;

import com.mrlu.springboot.bean.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-16 20:04
 *
 * @Mapper 告诉mybatis这是一个mapper接口
 * 或者在SpringBoot的主配置类加上这个注解，扫描mapper接口所在的包
 * @MapperScan(basePackages = {"com.mrlu.springboot.dao"})
 */
//@Mapper
public interface StudentMapper {

    /**
     * 查询单个学生
     * @param id
     * @return
     */
    Student getStudent(Integer id);
}
