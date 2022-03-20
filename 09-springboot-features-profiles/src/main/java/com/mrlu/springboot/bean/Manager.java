package com.mrlu.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-19 18:36
 *
 * myprod环境下测试。但是它的配置文件的内容被config/application.yaml的覆盖了
 */
@Profile(value = {"myprod","default"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@ConfigurationProperties("person")
public class Manager{
    private String name;
    private String age;
}
