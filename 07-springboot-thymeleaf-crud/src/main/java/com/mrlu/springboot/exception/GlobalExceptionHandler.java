package com.mrlu.springboot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-18 17:39
 *
 * 第一种处理异常的方式
 * 使用以下两个注解
 * @ControllerAdvice
 * @ExceptionHandler
 *
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})
    public String handlerException(Exception exception){
        log.info("异常信息是：",exception);
        //视图地址
        return "login";
    }
}
