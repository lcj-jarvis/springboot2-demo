package com.mrlu.redis.controller;

import com.mrlu.redis.service.SecKillService;
import com.mrlu.redis.service.SecKillServiceByScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @author 简单de快乐
 * @date 2021-07-25 22:34
 *
 *  redis秒杀案例
 */
@RestController
public class SeckillController {

    @Autowired
    private SecKillService secKillService;

    @Autowired
    private SecKillServiceByScript secKillServiceByScript;

    @PostMapping("/doseckill")
    public String secKill(@RequestParam("prodid") String prodid) {
        String userid = new Random().nextInt(50000) + "" ;

        //boolean isSuccess = secKillService.doSecKill(userid,prodid);

        //通过lua脚本实现秒杀，解决库存遗留问题
        boolean isSuccess = secKillServiceByScript.doSecKill(userid,prodid);

        return isSuccess? "success" : "fail";
    }
}
