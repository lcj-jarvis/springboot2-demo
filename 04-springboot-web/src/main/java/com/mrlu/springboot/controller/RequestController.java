package com.mrlu.springboot.controller;

import com.mrlu.springboot.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-12 20:15
 */
@Controller
public class RequestController{

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("msg","成功了。。。");
        request.setAttribute("code",200);
        return "forward:/success";
    }

    /**
     * @RequestAttribute 注解可以获取请求域里的值
     */
    @ResponseBody
    @GetMapping("/success")
    public Map<String, Object> success(@RequestAttribute String msg,
                                       @RequestAttribute Integer code,
                                       HttpServletRequest request){
        Object msg1 = request.getAttribute("msg");
        Map<String, Object> map = new HashMap<>();
        map.put("HttpServletRequest_msg",msg1);
        map.put("Annotation_msg",msg);
        return  map;
    }

    @GetMapping("/params")
    public String testParam(Map<String, Object> map,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response){
        map.put("hello","world666");
        model.addAttribute("world","hello666");
        request.setAttribute("ccc","666");
        Cookie cookie = new Cookie("c1", "v1");
        //设置范围
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "forward:/testParams";
    }

    @GetMapping("/testParams")
    @ResponseBody
    public Map<String, Object> getResult(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>(16);
        Object hello = request.getAttribute("hello");
        Object world = request.getAttribute("world");
        Object ccc = request.getAttribute("ccc");
        Cookie[] cookies = request.getCookies();
        map.put("hello",hello);
        map.put("world",world);
        map.put("ccc",ccc);
        map.put("cookies",cookies);
        return map;
    }

    /**
     * 测试POJO类型参数的绑定
     * 数据绑定：页面提交的请求数据（GET、POST）都可以和对象属性进行赋值
     * @param person 可以自动类型转换与格式化，可以级联封装
     * @return
     */
    @PostMapping("/savePerson")
    @ResponseBody
    public Person savePerson(Person person){
        return person;
    }
}
