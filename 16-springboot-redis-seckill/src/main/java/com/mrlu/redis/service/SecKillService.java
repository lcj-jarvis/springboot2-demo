package com.mrlu.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 简单de快乐
 * @date 2021-07-25 22:41
 *
 *
 * 1、编写代码
 * 2、安装ab并发工具测试
 *    yum install httpd-tools
 * 3、查看并发工具的帮助命令
 *    ab --help帮助命令
 *
 * 4、启动redis
 *    进入redis的安装目录
 *    cd /usr/local/bin
 *    redis-server conf/redis.conf
 *    设置 set sk:0101:qt 6 库存
 *
 * 5、编写postfile保存请求参数
 *     vim /usr/local/bin/postfile
 *     加入以下内容
 *     prodid=0101&
 *     然后保存并退出
 *
 * 6、启动springboot工程
 *
 * 7、发起请求
 *    ab -n 2000 -c 200 -k -p /usr/local/bin/postfile -T application/x-www-form-urlencoded http://192.168.1.102:8081/doseckill
 *
 * 8、 在redis的客户端运行
 *      get sk:0101:qt 查看库存
 *      keys * 查看秒杀成功的用户
 *
 * ==================================================================================================
 * ab --help
 *     -n requests     Number of requests to perform 【请求的数量】
 *     -c concurrency  Number of multiple requests to make at a time 【并发的数量】
 *     -t timelimit    Seconds to max. to spend on benchmarking
 *                     This implies -n 50000
 *     -s timeout      Seconds to max. wait for each response
 *                     Default is 30 seconds
 *     -b windowsize   Size of TCP send/receive buffer, in bytes
 *     -B address      Address to bind to when making outgoing connections
 *     -p postfile     File containing data to POST. Remember also to set -T 【保存Post请求的参数的文件】
 *     -u putfile      File containing data to PUT. Remember also to set -T
 *     -T content-type Content-type header to use for POST/PUT data, eg. 【设置content-type】
 *                     'application/x-www-form-urlencoded'
 *                     Default is 'text/plain'
 *     -v verbosity    How much troubleshooting info to print
 *     -w              Print out results in HTML tables
 *     -i              Use HEAD instead of GET
 *     -x attributes   String to insert as table attributes
 *     -y attributes   String to insert as tr attributes
 *     -z attributes   String to insert as td or th attributes
 *     -C attribute    Add cookie, eg. 'Apache=1234'. (repeatable)
 *     -H attribute    Add Arbitrary header line, eg. 'Accept-Encoding: gzip'
 *                     Inserted after all normal header lines. (repeatable)
 *     -A attribute    Add Basic WWW Authentication, the attributes
 *                     are a colon separated username and password.
 *     -P attribute    Add Basic Proxy Authentication, the attributes
 *                     are a colon separated username and password.
 *     -X proxy:port   Proxyserver and port number to use
 *     -V              Print version number and exit
 *     -k              Use HTTP KeepAlive feature 【所有http心跳检测】
 *     -d              Do not show percentiles served table.
 *     -S              Do not show confidence estimators and warnings.
 *     -q              Do not show progress when doing more than 150 requests
 *     -g filename     Output collected data to gnuplot format file.
 *     -e filename     Output CSV file with percentages served
 *     -r              Don't exit on socket receive errors.
 *     -h              Display usage information (this message)
 *     -Z ciphersuite  Specify SSL/TLS cipher suite (See openssl ciphers)
 *     -f protocol     Specify SSL/TLS protocol
 *                     (SSL3, TLS1, TLS1.1, TLS1.2 or ALL)
 *
 *
 *   加事务-乐观锁(解决超卖),但出现遗留库存
 *
 *   所有的请求结束了，可是还有库存。原因，就是乐观锁导致很多请求都失败。先点的没秒到，后点的可能秒到了。
 */
@Service
public class SecKillService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 秒杀过程
     * @param userId
     * @param prodid
     * @return
     */
    public boolean doSecKill(String userId, String prodid) {
        //1 uid和prodid非空判断
        if (userId == null || prodid == null) {
            return false;
        }
        //2 拼接key
        // 2.1 库存key
        String storeKey = "sk:" + prodid + ":qt";
        // 2.2 秒杀成功用户key
        String userKey = "sk:"  + userId + ":user";


        //4 获取库存，如果库存null，秒杀还没有开始
        Integer store = (Integer) redisTemplate.opsForValue().get(storeKey);
        if (store == null) {
            System.out.println("秒杀活动还没开始。。。");
            return false;
        }

        // 5 判断用户是否重复秒杀操作
        boolean secKill = redisTemplate.opsForSet().isMember(userKey, userId);
        if (secKill) {
            System.out.println("已经秒杀成功，不能重复秒杀。。。");
            return false;
        }

        //6 判断如果商品数量，库存数量小于1，秒杀结束
        if (store < 1) {
            System.out.println("秒杀结束。。。");
            return false;
        }


        List<Object> result = (List<Object>) redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                //redisTemplate.setEnableTransactionSupport(true);
                redisTemplate.watch(storeKey);
                operations.multi();
                operations.opsForValue().decrement(storeKey);
                operations.opsForSet().add(userKey, userId);
                return operations.exec();
            }
        });

        if (result == null || result.size() == 0) {
            System.out.println("秒杀失败。。。");
            return false;
        }

        System.out.println("秒杀成功。。。。");
        return true;
    }
}
