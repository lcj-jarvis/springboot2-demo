package com.mrlu.springwebflux.service.impl;

import com.mrlu.springwebflux.bean.User;
import com.mrlu.springwebflux.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-21 10:02
 */
@Service
public class UserServiceImpl implements UserService {

    private Map<Integer,User> map = new HashMap<>();

    public UserServiceImpl() {
        map.put(1,new User("jack","男",25));
        map.put(2,new User("marry","女",23));
        map.put(3,new User("Tom","男",23));
    }

    @Override
    public Mono<User> getUserById(Integer id) {
        return Mono.justOrEmpty(map.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(map.values());
    }

    @Override
    public Mono<Void> saveUser(Mono<User> userMono) {
        //thenEmpty(Mono.empty()); 表示终止信号
        Mono<Void> finish = userMono.doOnNext((user) -> {
            Integer id = map.size() + 1;
            map.put(id, user);
        }).thenEmpty(Mono.empty());
        //System.out.println(map.values());
        return finish;
    }
}
