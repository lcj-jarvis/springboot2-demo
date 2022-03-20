package com.mrlu.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-16 20:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class City {
    private Long id;
    private String name;
    private String state;
    private String country;

}
