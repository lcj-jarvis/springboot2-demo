package com.mrlu.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 简单de快乐
 * @date 2021-07-26 22:32
 *
 * Redis 使用单个 Lua 解释器去运行所有脚本，并且，Redis也保证脚本会以原子性(atomic)的方式执行：
 * 当某个脚本正在运行的时候，不会有其他脚本或 Redis 命令被执行。【注意】
 * 这和使用 MULTI / EXEC 包围的事务很类似。
 * 在其他别的客户端看来，脚本的效果(effect)要么是不可见的(not visible)，要么就是已完成的(already completed)。
 * 另一方面，这也意味着，执行一个运行缓慢的脚本并不是一个好主意。写一个跑得很快很顺溜的脚本并不难，
 * 因为脚本的运行开销(overhead)非常少，但是当你不得不使用一些跑得比较慢的脚本时，
 * 请小心， 因为当这些蜗牛脚本在慢吞吞地运行的时候，其他客户端会因为服务器正忙而无法执行命令。
 *
 *
 * 从定义上来说， Redis 中的脚本本身就是一种事务，所以任何在事务里可以完成的事，在脚本里面也能完成。
 * 并且一般来说， 使用脚本要来得更简单，并且速度更快【注意】
 *
 * lua脚本的教程见https://www.runoob.com/lua/lua-environment.html
 *
 * 解决库存遗留的问题
 * 通过lua脚本解决争抢问题，实际上是redis 利用其单线程的特性，用任务队列的方式解决多任务并发问题
 */
@Service
public class SecKillServiceByScript {

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String SEC_KILL_SCRIPT = "local usersKey=KEYS[1];\n" +
            "local qtkey=KEYS[2];\n" +
            "local userid=ARGV[1];\n" +
            "local prodid=ARGV[2];\n" +
            "local userExists=redis.call(\"sismember\",usersKey,userid);\n" +
            "if tonumber(userExists)==1 then\n" +
            "  return 2;\n" +
            "end\n" +
            "local num = redis.call(\"get\",qtkey);\n" +
            "if tonumber(num) <=0 then\n" +
            "  return 0;\n" +
            "else\n" +
            "  redis.call(\"decr\",qtkey);\n" +
            "  redis.call(\"sadd\",usersKey,userid);\n" +
            "end\n" +
            "return 1;";

    /**
     * 秒杀过程
     * @param userId
     * @param prodid
     * @return
     */
    public boolean doSecKill(String userId, String prodid) {
        if (userId == null || prodid == null) {
            return false;
        }
        String storeKey = "sk:" + prodid + ":qt";
        String userKey = "sk:"  + userId + ":user";

        //方式一
        // 执行 lua 脚本
        //DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        // 指定 lua 脚本
        //redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/secKill.lua")));
        // 指定返回类型
        //redisScript.setResultType(Long.class);

        //方式二
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(SEC_KILL_SCRIPT, Long.TYPE);

        // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
        List<String> keys = new ArrayList<>();
        keys.add(userKey);
        keys.add(storeKey);
        Long result = (Long) redisTemplate.execute(redisScript, keys, userId, prodid);

        String msg;
        boolean success = false;
        switch (result.intValue()) {
            case 0:
                msg = "已抢空！！";
                success = false;
                break;
            case 1:
                msg = "抢购成功！！！！";
                success = true;
                break;
            case 2:
                msg = "该用户已抢过！！";
                success = false;
                break;
            default:
                msg = "抢购异常！！";
        }
        System.out.println(msg);
        return success;
    }

}
