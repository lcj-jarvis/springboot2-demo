package com.mrlu.springboot.redis;

import com.mrlu.springboot.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 简单de快乐
 * @date 2021-07-25 18:11
 *
 * jedis连接池
 */
public class JedisDemo03 {

    public static void main(String[] args) {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        for (int i = 0; i < 1000; i++) {
            Jedis client = jedisPool.getResource();
            client.set("key" + i, String.valueOf(i));
            client.close();
        }
    }
}
