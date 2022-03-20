package com.mrlu.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-21 16:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String usrName;
    private String email;
}
