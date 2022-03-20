package com.mrlu.springboot.controller;

import com.mrlu.springboot.bean.Team;
import com.mrlu.springboot.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-21 19:48
 *
 *
 * @RequestBody 主要用来接收前端传递给后端的json字符串中的数据（请求体中的数据）;
 * GET方式无请求体，所以使用@RequestBody接收书数据的时候，
 * 只能使用POST方式进行提交,而且只能接受Post请求体里的内容【前提】
 *
 * 1、一个请求对应的方法，只能有一个@RequestBody注解，可以有多个@RequestParam注解
 * 2、@RequestBody和@RequestParam同时使用时，@RequestParam指定的参数可以是普通元素，数组，集合，对象等等
 *    即：@RequestBody和@RequestParam同时使用时，原SpringMVC接收的参数机制不变。
 *    只不过@RequestBody接收的是请求体里面的数据，而@RequestParam接收的是请求参数key=value里面的参数。
 * 3、如果参数是放在请求体中，application/json传入后台的话，那么后台要用@RequestBody才能接收
 *    如果不是放在请求体中的，那么后台接收前台传过来的参数时，要用@RequestParam来接收，或
 *              则形参前 什么也不写也能接收。
 *
 * 4、如果参数前写了@RequestParam(xxx)，那么前端必须有对应的xxx名字才行(不管其是否有值，当然可以通
 *        过设置该注解的required属性来调节是否必须传)，如果没有xxx名的话，那么请求会出错，报400。
 *
 * 5、如果参数前不写@RequestParam(xxx)的话，那么就前端可以有可以没有对应的xxx名字才行，如果有xxx名
 *        的话，那么就会自动匹配；没有的话，请求也能正确发送（当时参数无法入参）。
 *
 * 6、如果后端参数是一个对象，且该参数前是以@RequestBody修饰的
 *   【如果是浏览器，还要求contextType=application/json】
 *    那么前端传递json参数时(前提)，必须满足以下要求：
 *   （1）@RequestBody后面的类)时，会根据json字符串中的key来匹配对应实体类的属性，
 *       如果匹配一致且json中的该key对应的值符合(或可转换为)，实体类的对应属性的类型要求时,
 *       会调用实体类的setter方法将值赋给该属性。
 *   （2）json字符串中，如果value为""的话，后端对应属性如果是String类型的，那么接受到的就是""，
 *         如果是后端属性的类型是Integer、Double等类型，那么接收到的就是null
 *   （3）json字符串中，如果value为null的话，后端对应收到的就是null。
 *   （4）如果某个参数没有value的话，在传json字符串给后端时，要么干脆就不把该字段写到json字符串中；
 *       要么写value时，必须有值，null或""都行。千万不能有类似 "stature": ，这样的写法
 */
@RestController
@Slf4j
public class RequestBodyController {

    /**
     * 请求http://localhost:8080/test01
     * 请求体：
     *{
     *   "value": [
     *     "Z"
     *   ],
     *   "hash": 1,
     *   "serialVersionUID": 1
     * }
     *
     *  响应
     * {
     *   "value": [
     *     "Z"
     *   ],
     *   "hash": 1,
     *   "serialVersionUID": 1
     * }
     * 使用idea的RestServices进行测试。
     * @param jsonString
     * @return
     */
    @PostMapping("/test01")
    public String method01(@RequestBody String jsonString){
      log.info(jsonString);
      return jsonString;
    }

    /**
     * http://localhost:8080/test02
     * 请求体：
     * {
     *   "name": "demoData",
     *   "age": 18,
     *   "gender": "男",
     *    "motto": "认清生活的真相，并且仍然热爱它"
     * }
     * 响应
     * {
     *   "name": "demoData",
     *   "age": 18,
     *   "gender": "男",
     *   "motto": "认清生活的真相，并且仍然热爱它"
     * }
     * @param user
     * @return
     */
    @PostMapping("/test02")
    public User method02(@RequestBody User user){
        log.info("user{}",user);
        return user;
    }

    /**
     * http://localhost:8080/test03?id=1
     * 请求体：
     * {
     *   "name": "demoData",
     *   "age": 18,
     *   "gender": "男",
     *    "motto": "认清生活的真相，并且仍然热爱它"
     * }
     * 响应：1:User(name=demoData, age=18, gender=男, motto=认清生活的真相，并且仍然热爱它)
     *
     * @param user
     * @param id
     * @return
     */
    @PostMapping("/test03")
    public String method03(@RequestBody User user, @RequestParam("id") String id){
        log.info("user{}",user);
        return id+":"+user;
    }

    /**
     * http://localhost:8080/test04?hobby=听音乐&hobby=看电影
     * 请求体：
     * {
     *   "name": "demoData",
     *   "age": 18,
     *   "gender": "男",
     *    "motto": "认清生活的真相，并且仍然热爱它"
     * }
     *
     * 当然也可以String类型的数组来接收
     * 结果：
     * [听音乐, 看电影]:User(name=demoData, age=18, gender=男, motto=认清生活的真相，并且仍然热爱它)
     * @param user
     * @param hobbies
     * @return
     */
    @PostMapping("/test04")
    public String method04(@RequestBody User user, @RequestParam("hobby") List<String> hobbies){
        log.info("user{}",user);
        return hobbies +":"+user;
    }

    /**
     *
     * 请求的url:
     * http://localhost:8080/test05
     * 请求体：
     * {
     *   "id": 1,
     *   "teamName": "地表最强战队",
     *   "honors": [
     *     "速度最快",
     *    "合作度最高",
     *    "高度最高"
     *   ],
     *   "teamMembers": [
     *      {
     *         "name": "jack",
     *     	"age": 18,
     * 	"gender": "男",
     * 	"motto": "认清生活的真相，并且仍然热爱它"
     *      },
     *      {
     *         "name": "marry",
     *     	"age": 18,
     * 	"gender": "女",
     * 	"motto": "冲冲冲"
     *      },
     *     {
     *         "name": "Tom",
     *     	"age": 18,
     * 	"gender": "男",
     * 	"motto": "666"
     *      }]
     * }
     * 响应：
     * Team(id=1, teamName=地表最强战队, honors=[速度最快, 合作度最高, 高度最高], teamMembers=[User(name=jack, age=18, gender=男, motto=认清生活的真相，并且仍然热爱它), User(name=marry, age=18, gender=女, motto=冲冲冲), User(name=Tom, age=18, gender=男, motto=666)])
     */
    @PostMapping("/test05")
    public String method05(@RequestBody Team team){
        log.info("team{}",team);
        return team.toString();
    }

}
