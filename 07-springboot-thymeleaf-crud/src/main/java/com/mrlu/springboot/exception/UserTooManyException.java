package com.mrlu.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-18 17:46
 *
 * 第二种异常的处理方式
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户太多了")
public class UserTooManyException extends RuntimeException{
    private final static long serialVersionUID = 88888L;
    public UserTooManyException() {
    }

    public UserTooManyException(String message) {
        super(message);
    }
}
