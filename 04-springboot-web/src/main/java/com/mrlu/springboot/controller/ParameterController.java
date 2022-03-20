package com.mrlu.springboot.controller;

import com.mrlu.springboot.bean.User;
import com.sun.org.apache.xpath.internal.operations.Variable;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-12 17:17
 */
@RestController
//@Controller
public class ParameterController {

    /**
     *
     * @param id
     * @param username
     * @param map @PathVariable 会将id和username封装到map中。
     *            如果方法参数为{@link java.util.Map Map <String，String>} ，
     *            则将使用所有路径变量名称和值来填充映射。
     *            即map的key是id，value是id的属性值
     *              map的key是username，value是username的属性值
     * @RequestHeader : 获取请求头
     *                  @RequestHeader("User-Agent") String userAgent 获取请求头User-Agent
     *
     *                  @RequestHeader的注解说明：
     *                  如果方法参数为{@link java.util.Map Map <String，String>}，
     *                  {@link org.springframework.util.MultiValueMap MultiValueMap <String，String>}，
     *                  或{@link org.springframework.http .HttpHeaders HttpHeaders}，
     *                  然后用所有标头名称和值填充映射。
     *                  如：
     *                  @RequestHeader Map<String, String> headers
     *                  把所有的请求头放到map中。map的key是请求头的名字，map的value是请求头的值
     *
     *  @RequestParam
     *       如果方法参数为{@link java.util.Map Map <String，String>}
     *       或 {@link org.springframework.util.MultiValueMap MultiValueMap <String，String>}
     *       并且未指定参数名称，然后在map参数中填充所有请求参数名称和值。
     *
     *  @CookieValue
     *  方法参数可以声明为类型{@link javax.servlet.http.Cookie}
     *  或cookie值类型（字符串，整数等）
     *
     *  @CookieValue Cookie cookie 这样就是获取到Cookie的
     *  @CookieValue("cookie的名字") String cookieValue 这样就是获取到Cookie的值
     */
    @GetMapping("car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id")Integer id,
                                      @PathVariable("username")String username,
                                      @PathVariable Map<String, String> map,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String, String> headers,
                                      @RequestParam("age")Integer age,
                                      @RequestParam("inters") List<String> inters,
                                      @RequestParam Map<String, String> params){
                                      //@CookieValue Cookie cookie){

         Map<String, Object> resultMap = new HashMap<>();
         /*resultMap.put("id",id);
         resultMap.put("username",username);
         resultMap.put("map",map);
         resultMap.put("userAgent",userAgent);
         resultMap.put("headers",headers);*/
         resultMap.put("age",age);
         resultMap.put("inters",inters);
         resultMap.put("params",params);
//         resultMap.put("cookie",cookie);
//         System.out.println(cookie);
         //System.out.println(cookie.getName()+"=====>"+cookie.getValue());
         return resultMap;
    }

    /**
     * URL乱码问题还没有解决:原因：表单发出的类型的类型如下。
     * application/x-www-form-urlencoded
     * 不是：application/json。 就会出现乱码。
     *
     * 而且使用了@RequestBody，只能发送POST请求才能接收到。
     * @param
     * @return
     * @throws UnsupportedEncodingException
     *
     */
    @RequestMapping(value ="/save")
    // 1 //public Map<String, Object> save(@RequestBody User user) throws UnsupportedEncodingException {
    //2
    public Map<String, Object> save(@RequestBody String user) throws UnsupportedEncodingException {

        Map<String, Object> map = new HashMap<>(16);
        System.out.println(user);
        map.put("user",user);
        return map;
    }

    /**
     *
     * 1、语法：/cars/sell;low=34;brand=byd,audi,yd
     *   【注意】矩阵变量的请求映射不能直接写
     *        /cars/sell
     * 2、SpringBoot默认是禁用了矩阵变量的功能
     *      手动开启：原理。对于路径的处理。UrlPathHelper进行解析
     *      UrlPathHelper的removeSemicolonContent的移除分号内容默认是true。要将它设置成false
     *    private boolean removeSemicolonContent = true;
     *    在SpringMVC的配置类中，自定义配置UrlPathHelper【见配置类】
     * 3、矩阵变量必须要有url路径变量才能被解析
     */
    //错误映射写法
    //@GetMapping("/cars/sell")
    //正确映射写法
    //@GetMapping("/cars/{path}")
    @GetMapping("/cars/{p}")
    public Map<String, Object> carsSell(@MatrixVariable("low") Integer low,
                                        @MatrixVariable("brand")List<String> brand,
                                        @PathVariable("p")String path){
        Map<String, Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);
        System.out.println(path);
        return map;
    }

    /**
     * /boss/1;age=20/2;age=10
     *
     */
    @GetMapping("/boss/{boss}/{emp}")
    public Map<String, Object> boss(@MatrixVariable(value = "age",pathVar = "boss")Integer bossAge,
                                    @MatrixVariable(value = "age",pathVar = "emp")Integer empAge){
        Map<String, Object> map = new HashMap<>();
        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;
    }
}
