package com.mrlu.springwebflux.handler;

import com.mrlu.springwebflux.bean.User;
import com.mrlu.springwebflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-21 12:34
 *
 * 【注意】路由中如果想找到这些方法，方法的参数必须有ServerRequest
 */
public class UserHandler {

    public UserService userService;

    public UserHandler() {
    }

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据id查询
     * @param request
     * @return ServerResponse.ok()表示响应ok
     *         contentType(MediaType.APPLICATION_JSON) 表示响应数据的媒体类型
     *         body(BodyInserters.fromValue(person))) 表示响应的内容
     *         switchIfEmpty(noFound); 如果空的话，响应什么
     */
    public Mono<ServerResponse> getUserById(ServerRequest request){

        System.out.println("=================getUserById===================");
        //获取id
        Integer id = Integer.valueOf(request.pathVariable("id"));

        //空值处理
        Mono<ServerResponse> noFound = ServerResponse.notFound().build();

        //调用server方法得到数据
        Mono<User> user = userService.getUserById(id);
        //把user进行转换
        //使用Reactor的操作符flatMap
        return  user.flatMap(person->ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(person)))
                .switchIfEmpty(noFound);
    }

    /**
     * 查询所有
     * @return
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request){
        System.out.println("=================getAllUser===================");

        //调用service返回结果
        Flux<User> allUser = userService.getAllUser();
        return  ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(allUser,User.class);
    }

    /**
     * 添加
     * @param request
     * @return
     */
    public Mono<ServerResponse> saveUser(ServerRequest request){
        System.out.println("=================getAllUser===================");

        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok()
                .build(userService.saveUser(userMono));
    }
}
