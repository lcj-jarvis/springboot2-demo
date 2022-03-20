package com.mrlu.springboot.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis连接池工具
 */
public class JedisPoolUtil{

	private static volatile JedisPool jedisPool = null;

	private JedisPoolUtil() {

	}

	public static JedisPool getJedisPoolInstance() {
		if (null == jedisPool) {
			synchronized (JedisPoolUtil.class) {
				if (null == jedisPool) {
					JedisPoolConfig poolConfig = new JedisPoolConfig();
					poolConfig.setMaxTotal(200);
					poolConfig.setMaxIdle(32);
					poolConfig.setMaxWaitMillis(100*1000);
					poolConfig.setBlockWhenExhausted(true);
					// ping  PONG
					poolConfig.setTestOnBorrow(true);
				 
					jedisPool = new JedisPool(poolConfig, "192.168.187.100",
							6379, 60000 );
				}
			}
		}
		return jedisPool;
	}

	public static void release(JedisPool jedisPool, Jedis jedis) {
		if (null != jedis) {
			jedis.close();
		}
		if (jedisPool != null) {
			jedisPool.close();
		}
	}
}
