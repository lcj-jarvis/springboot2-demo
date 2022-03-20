package com.mrlu.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 简单de快乐
 * @date 2021-07-28 20:37
 *
 * 分布式锁
 *
 * 在linux上使用ab工具进行并发测试。
 * ab -n 1000 -c 100 http://192.168.1.102:8081/testLock
 */
@Controller
public class LockController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 分布式锁的第一种实现方式
     * 存在问题就是如果有个请求down机，不能释放锁的话，
     * 就会造成死锁的问题。其他请求一直拿不到锁
     *
     * 解决办法：
     * 设置过期时间
     */
    /*@GetMapping("/testLock")
    public void testLock() {
        //获取锁，setnx
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "111");
        if (lock) {
            Integer num = (Integer) redisTemplate.opsForValue().get("num");
            //2.1 判断num为空 return
            if (Objects.isNull(num)) {
                return;
            }
            //2.3、把redis的num加一
            redisTemplate.opsForValue().set("num", ++num);

            System.out.println("======>" + num);

            //2.4、释放锁
            redisTemplate.delete("lock");
        } else {
            //3、获取锁失败，每隔0.1秒再获取
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }*/

    /**
     * 上锁的同时设置过期时间，解决死锁的问题。但是存在误删的问题
     * 原因是锁的value保存的是固定的内容无法判断谁占有锁。就会误删
     */
    /*@GetMapping("/testLock")
    public void testLock() {
        //获取锁，set nx ex 设置过期时间
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "111", 5, TimeUnit.SECONDS);
        if (lock) {
            Integer num = (Integer) redisTemplate.opsForValue().get("num");
            //2.1 判断num为空 return
            if (Objects.isNull(num)) {
                return;
            }
            //2.3、把redis的num加一
            redisTemplate.opsForValue().set("num", ++num);

            System.out.println("======>" + num);

            //2.4、释放锁
            redisTemplate.delete("lock");
        } else {
            //3、获取锁失败，每隔0.1秒再获取
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }*/

    /**
     * 用uuid表示不同的操作，防止锁的误删。但是这样还是存在问题
     */
    /*@GetMapping("/testLock")
    public void testLock() {
        //获取锁，set nx ex 设置过期时间
        String id = UUID.randomUUID().toString().substring(0, 6);
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", id, 5, TimeUnit.SECONDS);
        if (lock) {
            Integer num = (Integer) redisTemplate.opsForValue().get("num");
            //2.1 判断num为空 return
            if (Objects.isNull(num)) {
                return;
            }
            //2.3、把redis的num加一
            redisTemplate.opsForValue().set("num", ++num);

            System.out.println("======>" + num);

            //2.4、释放锁. 防止误删
            if (id.equals(redisTemplate.opsForValue().get("lock"))) {
                redisTemplate.delete("lock");
            }
        } else {
            //3、获取锁失败，每隔0.1秒再获取
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }*/

    public static final String SCRIPT = "if redis.call(\"get\",KEYS[1])==ARGV[1] then return redis.call(\"del\",KEYS[1]) else return 0 end;";

    /**
     * 使用lua脚本解决锁误删的问题
     */
    @GetMapping("/testLock")
    public void testLock() {
        //获取锁，set nx ex 设置过期时间
        String id = UUID.randomUUID().toString().substring(0, 6);
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", id, 5, TimeUnit.SECONDS);
        if (lock) {
            Integer num = (Integer) redisTemplate.opsForValue().get("num");
            //2.1 判断num为空 return
            if (Objects.isNull(num)) {
                return;
            }
            //2.3、把redis的num加一
            redisTemplate.opsForValue().set("num", ++num);

            System.out.println("======>" + num);

            RedisScript<Long> script = new DefaultRedisScript<>(SCRIPT, Long.TYPE);
            redisTemplate.execute(script, Arrays.asList("lock"), id);
        } else {
            //3、获取锁失败，每隔0.1秒再获取
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
