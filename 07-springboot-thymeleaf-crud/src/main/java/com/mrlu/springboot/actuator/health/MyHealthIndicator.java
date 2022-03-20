package com.mrlu.springboot.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-19 15:07
 *
 * 自定义健康信息。
 *   方式二：
 *   编写一个类实现HealthIndicator接口。重写health方法，定制健康规则
 */
@Component
public class MyHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = new Random().nextInt(2);
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", 500)
                    .withDetail("msg", "error service").build();
        }
        return Health.up().build();
    }
}
