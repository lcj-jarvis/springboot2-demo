package com.mrlu.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

//表示扫描com.mrlu.springboot包下的所有带有@WebServlet注解的Servlet
//@ServletComponentScan(basePackages = {"com.mrlu.springboot"})
@MapperScan("com.mrlu.springboot.dao")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
