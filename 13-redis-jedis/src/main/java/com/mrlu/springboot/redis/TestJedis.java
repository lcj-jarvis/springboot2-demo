package com.mrlu.springboot.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-25 10:46
 *
 * 连接linux上的redis。前提：将redis.conf配置文件的bind项注释掉，protected-mode设置为no，
 * 关闭linux的防火墙 systemctl stop firewalld 或者 开放6379端口。
 *
 */
public class TestJedis {

    public static void main(String[] args) {
        //第一个参数为相应的linux系统的ip地址
        Jedis jedis = new Jedis("192.168.174.128", 6379);
        //jedis.auth("123456");
        System.out.println(jedis.ping());

        //jedis的基本api和redis的是完全一样的
        /*jedis.lpush("list","1","2","3");
        List<String> list = jedis.lrange("list", 0, -1);
        list.forEach(System.out::println);*/

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","jack");
        jsonObject.put("age","18");
        String result = jsonObject.toJSONString();
        jedis.flushDB();
        //测试事务
        try {
            jedis.set("balance","100");
            jedis.set("dept","0");
            jedis.watch("balance","dept");
            //jedis.set("dept","10");

            Transaction multi = jedis.multi();
            multi.set("user:1", result);
            multi.decrBy("balance",10);
            multi.incrBy("dept",10);
            //int i = 100 / 0;
            multi.set("user:2", result);
            multi.exec();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user:1"));
            System.out.println(jedis.get("balance"));
            System.out.println(jedis.get("dept"));
            //关闭连接
            jedis.close();
        }

    }
}
