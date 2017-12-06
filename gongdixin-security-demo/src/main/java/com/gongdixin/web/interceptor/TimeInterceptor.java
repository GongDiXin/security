package com.gongdixin.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/21 23:33
 * @description  拦截器需要额外的配置 光申明为Spring组件是不行的
 */
@Component
public class TimeInterceptor implements HandlerInterceptor{

    //控制器方法执行前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        //handler 请求的控制器
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        request.setAttribute("startTime",new Date().getTime());
        return true;
    }

    //preHandle方法执行完以后调用postHandle 如果控制器抛出异常 则不会调用postHandle
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时:"+(new Date().getTime() - start));
    }

    //无论是否有异常 都会执行afterCompletion
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时:"+(new Date().getTime() - start));
        System.out.println("ex is" + ex);
    }
}
