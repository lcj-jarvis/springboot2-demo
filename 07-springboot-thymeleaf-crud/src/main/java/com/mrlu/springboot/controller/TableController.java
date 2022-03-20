package com.mrlu.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrlu.springboot.bean.User;
import com.mrlu.springboot.exception.UserTooManyException;
import com.mrlu.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-17 13:44
 */
@Controller
public class TableController {

    @Autowired
    private UserService userService;

    @GetMapping("/basic_table")
    //测试异常
    //public String basicTable(@RequestParam("a") int a){
    public String basicTable(){
        //测试异常
        //int i = 10/0;
        return "table/basic_table";
    }

    @GetMapping("/dynamic_table")
    public String dynamicTable(Model model,@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo) {
        //表格内容的遍历
        /*List<User> users = Arrays.asList(new User("jack", "123456"),
                new User("mary", "aabb"),
                new User("john", "aaaaa"),
                new User("lucy", "dddddddd"));
        model.addAttribute("users",users);*/

        //测试异常。第二、三种异常的处理方式
        /*if (users.size() > 3){
            throw new UserTooManyException();
        }*/

        //从数据库查出数据
        Page<User> page = userService.page(new Page<User>(pageNo, 2), null);
        List<User> records = page.getRecords();
        model.addAttribute("page",page);
        model.addAttribute("records",records);
        return "table/dynamic_table";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id")Integer id,
                             @RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,
                             RedirectAttributes redirectAttributes){
        userService.removeById(id);
        //添加重定向的参数,到下面重定向的url中
        redirectAttributes.addAttribute("pageNo",pageNo);
        return "redirect:/dynamic_table";
    }

    @GetMapping("/editable_table")
    public String editableTable(){

        return "table/editable_table";
    }

    @GetMapping("/responsive_table")
    public String responsiveTable(){
        return "table/responsive_table";
    }
}
