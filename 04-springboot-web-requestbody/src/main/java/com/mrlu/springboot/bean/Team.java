package com.mrlu.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-21 20:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Team {
    private Integer id;
    private String teamName;
    private List<String> honors;
    private List<User> teamMembers;
}
