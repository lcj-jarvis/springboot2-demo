package com.mrlu.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrlu.springboot.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-18 22:14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
