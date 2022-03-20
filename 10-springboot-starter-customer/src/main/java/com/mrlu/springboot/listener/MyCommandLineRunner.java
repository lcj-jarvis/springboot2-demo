package com.mrlu.springboot.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-20 19:22
 * 自定义CommandLineRunner
 */
@Order(2)
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    /**
     *
     * @param args 命令行的参数
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner...run...");
    }
}
