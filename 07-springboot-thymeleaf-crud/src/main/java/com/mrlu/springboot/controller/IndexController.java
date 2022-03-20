package com.mrlu.springboot.controller;

import com.mrlu.springboot.bean.Admin;
import com.mrlu.springboot.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-17 0:32
 */
@Controller
public class IndexController {

    /**
     * 来登录页
     */
    @GetMapping(value = {"/","/login"})
    public String loginPage(){

        return "login";
    }

    /**
     * 登录后，重定向到main.html,防止表单重复提交
     * @return
     */
    @PostMapping("/login")
    public String main(Admin admin, HttpSession session, Model model){

        //if (StringUtils.hasText(user.getUsername()) && "123456".equals(user.getPassword())){
        if (StringUtils.hasText(admin.getUsername())){
           session.setAttribute("loginUser",admin);

            /**
             * 这样子，就相当于重新发一个/main请求给服务器，而不是跳转到main页面。所以不行
             */
            //return "redirect:main";
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","账号密码错误");
            //重新回到登录页面
            return "login";
        }
    }

    /**
     * 登录成功进入主页
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){
        //是否登录。拦截器。过滤器
        return "main";
    }


    /**
     * 退出登录页.清除session里的内容。再次回到登录页
     */
    @GetMapping(value = {"/logout"})
    public String logOut(HttpSession session){
        session.removeAttribute("loginUser");
        return "login";
    }
}


