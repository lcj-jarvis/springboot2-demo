package com.mrlu.springboot.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-18 17:58
 *
 * 第三种处理异常的方式
 * 自定义异常解析器。
 * 使用@Order(value = Ordered.HIGHEST_PRECEDENCE)将优先级设置成最高
 * 在SpringMVC底层的两个默认异常解析器之前先执行，返回ModelAndView，下面的两个默认异常解析器就不会执行
 *      DefaultErrorAttributes
 *      HandlerExceptionResolverComposite
 *
 *  按照下面的写法，不管发生什么异常都是520的错误码。
 *
 *  优先级，数字越小，优先级高
 *
 *  【测试的时候再把@Order注释打开】
 */
//@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
public class CustomerHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("发生异常的对象："+handler);
        try {
            response.sendError(520,"我喜欢的异常");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
