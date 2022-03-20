package com.mrlu.springboot.service;

import com.mrlu.springboot.bean.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-19 20:27
 *
 *默认不要放在容器中
 */
public class HelloService {

    @Autowired
    private HelloProperties helloProperties;

    public String sayHello(String username){
        return  helloProperties.getPrefix()+"："+username+"》"+helloProperties.getSuffix();
    }
}
