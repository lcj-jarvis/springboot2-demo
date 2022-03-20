package com.mrlu.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 要想jsp页面被解析，在Edit configuration 中配置Environment的work directory为
 *  * $MODULE_WORKING_DIR$
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
