package com.mrlu.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrlu.springboot.bean.User;
import com.mrlu.springboot.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.ArrayList;

@SpringBootTest
class ApplicationTests {

	@Autowired
	@Qualifier("redisTemplate")
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private RedisUtil redisUtil;

	@Test
	void contextLoads() throws JsonProcessingException {
		  /*
		  RedisTemplate 操作不同的数据类型，api和我们的指令一样的。
		    redisTemplate.opsForValue(); 操作字符串的，类似于String
			redisTemplate.opsForList(); 类似于List
			redisTemplate.opsForSet(); 类似于Set
			redisTemplate.opsForHash(); 类似于hash
			redisTemplate.opsForGeo(); 类似于geo
			redisTemplate.opsForZSet(); 类似于zset
			redisTemplate.opsForHyperLogLog(); 类似于HyperLogLog

			redis常用的api
			redisTemplate.delete();
			redisTemplate.expireAt();
			redisTemplate.multi();
			redisTemplate.discard();
			redisTemplate.exec();
			 redisTemplate.watch();
             redisTemplate.unwatch();
			redisTemplate.getClientList();
			RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
			connection.close();
			connection.flushDb();
			connection.flushAll();
		 */

		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
		System.out.println(redisTemplate.getConnectionFactory());
		System.out.println(connection);
		//connection.flushDb();

		/*redisTemplate.opsForValue().set("name","李华");
		System.out.println(redisTemplate.opsForValue().get("name"));*/


		User user = new User("李华", 25);
		//传递对象的第一种方式，前提是该pojo实现了序列化的接口。
		//redisTemplate.opsForValue().set("user",user);
		//User(name=jack, age=25) 没有自定义序列化配置的时候返回的结果.
		// 此时用Xshell5打开，连接linux访问，使用keys * ，还是发现是转义后的字符
		//System.out.println(redisTemplate.opsForValue().get("user"));

		//真实的开发，一般都会使用json来传递对象
		//ObjectMapper objectMapper = new ObjectMapper();
		//String jsonUser = objectMapper.writeValueAsString(user);
        //redisTemplate.opsForValue().set("user",jsonUser);
        //{"name":"jack","age":25}
		//System.out.println(redisTemplate.opsForValue().get("user"));

		//自定义序列化配置

		/*
		此时用Xshell5打开，连接linux访问，使用keys * ，还是发现不是转义后的字符
		  localhost:6379> keys *
		  1) "user"
		*/
		redisTemplate.opsForValue().set("user",user);
		user = (User) redisTemplate.opsForValue().get("user");
		System.out.println(user.getName() +"====>"+user.getAge());
		//redisUtil.set("user",user);
		//System.out.println(redisUtil.get("ccc"));
		connection.close();

		RedisScript script = new RedisScript() {
			@Override
			public String getSha1() {
				return null;
			}

			@Override
			public Class getResultType() {
				return null;
			}

			@Override
			public String getScriptAsString() {
				return null;
			}
		};

        redisTemplate.execute(script, new ArrayList<>());
	}
}
