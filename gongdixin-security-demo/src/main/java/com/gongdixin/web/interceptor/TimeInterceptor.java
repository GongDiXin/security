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


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 控制器方法执行前调用
        System.out.println("preHandle");
        //handler 请求的控制器
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // preHandle方法执行完以后调用postHandle 如果控制器抛出异常 则不会调用postHandle
        System.out.println("postHandle");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时:"+(System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 无论是否有异常 都会执行afterCompletion
        System.out.println("afterCompletion");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时:"+(System.currentTimeMillis() - start));
        System.out.println("ex is" + ex);
    }
}
