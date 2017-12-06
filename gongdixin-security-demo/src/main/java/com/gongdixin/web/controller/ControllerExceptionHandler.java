package com.gongdixin.web.controller;

import com.gongdixin.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/20 22:04
 * @description 自定义异常处理类
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    /*@ExceptionHandler表示用于处理哪种异常抛出 程序任何方法抛出这种异常都会被捕获并进入该处理方法
    @ResponseBody表示将返回的数据以Json格式返回
    @ResponseStatus指定返回的状态码*/
    public Map<String,Object> handlerUserNotExistException(UserNotExistException ex){
        Map<String,Object> result = new HashMap<>();
        result.put("id",ex.getId());
        result.put("message",ex.getMessage());
        return result;
    }
}
