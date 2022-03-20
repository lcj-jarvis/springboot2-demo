package com.mrlu.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrlu.springboot.bean.User;
import com.mrlu.springboot.dao.UserMapper;
import com.mrlu.springboot.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-18 22:30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements UserService {

}
