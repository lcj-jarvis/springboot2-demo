package com.mrlu.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 简单de快乐
 * @date 2021-07-30 0:29
 *
 * springboot整合redis集群
 */
@Controller
@Slf4j
public class ClusterController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/redis/{key}/{value}")
    public void setMsg(@PathVariable("key") String key, @PathVariable("value") String value) throws InterruptedException {
        log.info("key:{},value:{}", key, value);
        //redisTemplate.opsForValue().set(key, value);
        //TimeUnit.SECONDS.sleep(1);
        //log.info("get success, value:{}", redisTemplate.opsForValue().get(key));

        //使用客户端的方式就不用创建组，实际上是将拆成了多条set执行
        Map<String, String> map = new HashMap<>(16);
        for (int i = 0; i < 3; i++) {
            map.put(key + i, value + i);
        }
        redisTemplate.opsForValue().multiSet(map);
        TimeUnit.SECONDS.sleep(1);
        List<String> list = redisTemplate.opsForValue().multiGet(map.keySet());
        list.forEach(System.out::println);
    }
}
