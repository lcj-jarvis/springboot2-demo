package com.mrlu.spring;

import com.mrlu.spring.bean.User;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
class ApplicationTests {

   /* @Autowired
    StatefulRedisClusterConnection connection;*/
   /* @Test
    void contextLoads(){
        String key = "10010";
        log.info("read from replica...");
        log.info("connection class is: {}", connection.getClass());
        RedisAdvancedClusterCommands sync = connection.sync();

        sync.set(key, "中国联通");
        Object value = sync.get(key);
        log.info("value is: {}", value);
    }*/

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Test
	void contextLoads1() throws InterruptedException {
        /*redisTemplate.opsForValue().set("abc1","6130136");
        redisTemplate.opsForValue().set("abc2","achqpiqic");
        redisTemplate.opsForValue().set("abc3","cqcqcqq");
        redisTemplate.opsForValue().set("abc4","nciqcqfqio");
        redisTemplate.opsForValue().set("abc5","cnicqcqdq");
        redisTemplate.opsForValue().set("abc6","3cnqicq");
        redisTemplate.opsForValue().set("abc7","dcqipcqpcq");
        redisTemplate.opsForValue().set("abc8","ddcqipcqq");
        redisTemplate.opsForValue().set("abc8","wwwwwwwwwwwww");
        redisTemplate.opsForValue().set("abc111111","wwwwwwwwwwwww");
		System.out.println(redisTemplate.opsForValue().get("k2"));
		System.out.println(redisTemplate.opsForValue().get("abc1"));
		System.out.println(redisTemplate.opsForValue().get("abc2"));
		System.out.println(redisTemplate.opsForValue().get("abc3"));
		System.out.println(redisTemplate.opsForValue().get("abc4"));
		System.out.println(redisTemplate.opsForValue().get("abc5"));
		System.out.println(redisTemplate.opsForValue().get("abc6"));*/
		User jack = new User("jack", 18);
		redisTemplate.opsForValue().set("jack",jack);
		System.out.println(redisTemplate.opsForValue().get("jack"));
	}

}
