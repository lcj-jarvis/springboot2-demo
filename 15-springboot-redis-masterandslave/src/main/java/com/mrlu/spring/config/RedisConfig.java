package com.mrlu.spring.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Cluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClusterConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.ArrayList;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-26 15:35
 *
 *
 */
@Configuration
@Slf4j
public class RedisConfig {

    @Value("${spring.redis.sentinel.nodes}")
    private String sentinels;

    @Value("${spring.redis.sentinel.master}")
    private String master;

    //这种方式只能完成复制，不能完成读分离
    /*@Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
        //配置哨兵
        sentinelConfiguration.master(master);
        String[] sentinelIps = sentinels.split(",");
        for (String sentinelIp:sentinelIps){
            String[] item = sentinelIp.split(":");
            String ip = item[0];
            String port = item[1];
            sentinelConfiguration.addSentinel(new RedisNode(ip,Integer.parseInt(port)));
        }
        return new JedisConnectionFactory(sentinelConfiguration);
    }*/


    /**
     * 可以完成读写分离
     * @return
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(){
        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
        //配置哨兵
        sentinelConfiguration.master(master);
        String[] sentinelIps = sentinels.split(",");
        for (String sentinelIp:sentinelIps){
            String[] item = sentinelIp.split(":");
            String ip = item[0];
            String port = item[1];
            sentinelConfiguration.addSentinel(new RedisNode(ip,Integer.parseInt(port)));
        }
        //配置在从库中读
        LettuceClientConfiguration clientConfiguration
                = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED ).build();

        return new LettuceConnectionFactory(sentinelConfiguration,clientConfiguration);
    }

    /**
     * 固定好的模板，可以直接使用
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        //我们为了自己开发方便，一般直接使用<String,Object>
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        //配置具体的序列化方式
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(),ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //hash的key采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        //value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.setConnectionFactory(redisConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }
}
