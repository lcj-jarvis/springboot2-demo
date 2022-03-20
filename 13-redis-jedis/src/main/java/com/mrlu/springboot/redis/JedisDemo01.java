package com.mrlu.springboot.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 简单de快乐
 * @date 2021-07-24 0:59
 *
 */
public class JedisDemo01 {

    public static void main(String[] args) {
        Jedis cli = new Jedis("192.168.187.100", 6379);
        //System.out.println(cli.ping());
        Map<String, String> fields = new HashMap<>(16);
        fields.put("name", "jack");
        fields.put("age", "18");
        cli.hset("user", fields);
        Map<String, String> user = cli.hgetAll("user");
        user.forEach((k,v) -> System.out.println(k + " : " + v));
        cli.close();


    }
}
