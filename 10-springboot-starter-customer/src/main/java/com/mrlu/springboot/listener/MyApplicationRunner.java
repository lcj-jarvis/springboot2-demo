package com.mrlu.springboot.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-20 19:21
 *
 * 自定义ApplicationRunner
 * @Order(1) 表示优先级，数字越小优先级也高。
 */
@Order(1)
@Component
public class MyApplicationRunner implements ApplicationRunner {
    /**
     *
     * @param args 命令行的参数
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("MyApplicationRunner...run...");
    }
}
