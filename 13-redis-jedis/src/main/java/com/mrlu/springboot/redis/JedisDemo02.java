package com.mrlu.springboot.redis;

import redis.clients.jedis.Jedis;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 简单de快乐
 * @date 2021-07-25 14:27
 *
 *
 * 1、输入手机号，点击发送后随机生成6位数字码，2分钟有效
 * 2、输入验证码，点击验证，返回成功或失败
 * 3、每个手机号每天只能输入3次
 *
 *
 * 解决思路：
 * 1、生成六位的随机数使用ThreadLocalRandom
 * 2、验证码在两分钟内失效
 *    把验证码保存到redis中，设置设置过期时间为120s
 * 3、判断验证码是否一致
 *    从redis中获取验证码和输入的验证码进行比较
 * 4、每个手机号每天只能发送3次
 *    incr每次发送之后加1
 *    大于2的时候，提交不能发送
 */
public class JedisDemo02 {

    private static Jedis client = new Jedis("192.168.187.100", 6379);

    public static void main(String[] args) {
        //System.out.println(getCode());
        verifyPhone("18820459293");
        //verifyCode("18820459293", "826530");
    }

    /**
     * 验证验证码是否正确
     */
    public static void verifyCode(String phone, String code) {
        //从redis中获取验证码
        String codeKey = "VerifyCode:" + phone + ":code";
        String redisCode = client.get(codeKey);
        if (code.equals(redisCode)) {
            System.out.println("成功");
        } else {
            System.out.println("失败");

        }
    }

        /**
         * 验证手机发送次数
         * @param phone
         */
    public static void verifyPhone(String phone) {
        //手机发送次数验证
        String countKey = "VerifyCode:" + phone + ":count";
        //验证码key
        String codeKey = "VerifyCode:" + phone + ":code";

        //每个手机号每天只能发送三次
        String count = client.get(countKey);
        if (count == null) {
            //没有发送次数，第一次发送
            //设置发送次数为1
            client.setex(countKey, 24*60*60, "1");
        } else if (Integer.parseInt(count) <= 2){
            //发送次数加一
            client.incr(countKey);
        } else {
            //发送次数大于1
            System.out.println("今天的发送次数已经大于三次");
            client.close();
        }

        //获取验证码
        String code = getCode();
        client.set(codeKey, code);
        client.close();
    }

    /**
     * 获取验证码
     * @return
     */
    public static String getCode() {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int nextInt = localRandom.nextInt(10);
            code.append(nextInt);
        }
        return code.toString();
    }
}
