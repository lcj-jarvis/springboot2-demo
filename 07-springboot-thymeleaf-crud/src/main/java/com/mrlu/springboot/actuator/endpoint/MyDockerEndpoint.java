package com.mrlu.springboot.actuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-19 16:01
 *
 * 自定义监控端点
 * 1、在类上使用@Endpoint(id = "container")，id表示监控点的名字
 * 2、编写两个方法，一个方法用于在监控上显示信息，使用@ReadOperation注解。
 *    另一个方法使用@WriteOperation方法。
 * 在浏览器上输入http://localhost:8080/actuator/container就可以看到结果。
 * 【注意，前提是开启了监控点才可以】
 */
@Component
@Endpoint(id = "container")
public class MyDockerEndpoint {

    @ReadOperation
    public Map getDockerInfo(){
        return Collections.singletonMap("info","Docker started....");
    }

    @WriteOperation
    public void restartDocker(){
        System.out.println("docker restarted");
    }

}
