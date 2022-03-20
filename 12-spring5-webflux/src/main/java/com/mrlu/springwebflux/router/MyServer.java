package com.mrlu.springwebflux.router;

import com.mrlu.springwebflux.handler.UserHandler;
import com.mrlu.springwebflux.service.impl.UserServiceImpl;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;


/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-21 13:20
 */
public class MyServer {

    public UserHandler handler;

    //响应式编程的第二种启动方式。
    public static void main(String[] args) {
        MyServer server = new MyServer();
        server.createReactorServer();
    }

    public MyServer() {
        handler = new UserHandler(new UserServiceImpl());
    }

    public MyServer(UserHandler handler) {
        this.handler = handler;
    }

    /**
     * 1、创建Router路由
     *RouterFunctions.route(..)
     *
     * RequestPredicates.GET("/user/{id}") 表示发送get请求，请求的uri为/user/{id}
     * RequestPredicates.accept(MediaType.APPLICATION_JSON) 表示客户端接收的媒体类型
     *
     * userHandler::getUserById 函数式接口，表示该请求映射的Handler的方法
     * @return
     */
    public RouterFunction<ServerResponse> routingFunction(){
        //设置路由
        return  RouterFunctions.route(
                GET("/users/{id}").and(accept(APPLICATION_JSON)),handler::getUserById)
                //继续添加路由
                .andRoute(GET("/users")
                        .and(accept(APPLICATION_JSON))
                        , handler::getAllUser)
                //继续添加路由
                .andRoute(RequestPredicates
                        .POST("/users")
                        .and(RequestPredicates.accept(APPLICATION_JSON))
                        , handler::saveUser);
    }

    /**
     *  2、创建服务器，完成适配
     *
     *  怎么启动服务器呢？获取到该bean，调用createReactorServer方法。
     */
    public void createReactorServer(){
        //路由和handler适配
        RouterFunction<ServerResponse> route = routingFunction();

        //创建适配器
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        //创建服务器
        HttpServer httpServer = HttpServer.create();
        System.out.println("==========================================>"+httpServer);
        //将服务器和适配器进行绑定
        httpServer.port(8686).handle(adapter).bindNow();
    }
}
