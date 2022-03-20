package com.mrlu.springboot.actuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-19 14:54
 *
 * 自定义健康信息。
 * 方式一：
 * 编写一个类继承AbstractHealthIndicator。实现doHealthCheck方法，定制健康的信息
 */
@Component
public class MyComHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        Map<String,Object> map = new HashMap<>();
        int code = new Random().nextInt(20);
        if (code >= 10){
            //健康
            //builder.up();
            //或者这样写
            map.put("count",1);
            map.put("ms",100);
            builder.status(Status.UP);
        }else {
            builder.status(Status.OUT_OF_SERVICE);
            map.put("error","连接超时");
            map.put("ms",3000);
        }

        //健康的细节信息
        builder.withDetail("code",200).withDetails(map);
    }
}
