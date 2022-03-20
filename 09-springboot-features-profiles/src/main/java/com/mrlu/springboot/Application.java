package com.mrlu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		Map<String, Object> systemEnvironment = context.getEnvironment().getSystemEnvironment();
		Map<String, Object> systemProperties = context.getEnvironment().getSystemProperties();
		systemEnvironment.forEach((k,v)-> System.out.println(k+"===>"+v));
		System.out.println("=======================================================");
		systemProperties.forEach((k,v)-> System.out.println(k+"===>"+v));
	}

}
