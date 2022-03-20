package com.mrlu.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-18 14:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    private String username;
    private String email;
}
