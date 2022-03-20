package com.mrlu.springboot.actuator.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-19 15:43
 * 发起多次http://localhost:8080/testCountRequest请求
 *
 * 去这里http://localhost:8080/actuator/metrics/myservice.method.running.counter看请求的次数
 */
@Component
public class MyService {

    /**
     * 统计请求发起了多少次
     */
    Counter counter;
    public MyService(MeterRegistry meterRegistry){
        counter = meterRegistry.counter("myservice.method.running.counter");

    }

    public String hello() {
        counter.increment();
        return "hello";
    }
}
