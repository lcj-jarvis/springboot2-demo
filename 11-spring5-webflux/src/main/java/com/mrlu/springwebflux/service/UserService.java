package com.mrlu.springwebflux.service;

import com.mrlu.springwebflux.bean.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-21 9:57
 *
 * 用户操作接口
 */
public interface UserService {
    /**
     * 根据id查询用户
     * @param id  用户id
     * @return 返回0个或者1个用户
     */
    Mono<User> getUserById(Integer id);

    /**
     * 查询所有的用户
     * @return 返回所有的用户
     */
    Flux<User> getAllUser();

    /**
     * 保存单个用户
     * @param userMono 单个用户
     * @return 返回值为void
     */
    Mono<Void> saveUser(Mono<User> userMono);
}
