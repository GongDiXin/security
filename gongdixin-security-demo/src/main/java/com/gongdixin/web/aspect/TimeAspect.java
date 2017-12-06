package com.gongdixin.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/22 21:23
 * @description  时间切面
 */
//@Aspect
//@Component
public class TimeAspect {

    /*第一个*表示任何返回值
    第二个*表示该包下面的任何方法
    ..表示任意个数的参数
    可以在spring官网查询更多写法 spring.io*/

    /*filter可以拿到http请求相关的信息 但是拿不到被拦截的方法的信息
    inspect可以拿到http请求相关的信息 也可以拿到被拦截的方法的信息  方法名或者所属的类 但是拿不到方法的参数信息
    aspect可以拿到方法的参数信息 但是拿不到http请求信息*/

    /**
     * @author GongDiXin
     * @date 2017/11/22 21:44
     * @description
     * @param point  上下文信息 拦截方法的信息
     * @return
    */
    @Around("execution(* com.gongdixin.web.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint point) throws Throwable{
        System.out.println("time aspect start");
        Object[] args = point.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }
        long start = new Date().getTime();
        Object obj = point.proceed();
        System.out.println("do aspect 耗时:"+(new Date().getTime() - start));
        System.out.println("time aspect end");
        return obj;
    }
}
