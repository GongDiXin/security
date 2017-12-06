package com.gongdixin.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/20 22:33
 * @description
 */
//@Component 在webConfig配置类中进行类的实例化
public class TimeFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter is init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TimeFilter start");
        long start = new Date().getTime();
        chain.doFilter(request,response);
        System.out.println("do filter 耗时:"+(new Date().getTime() - start));
        System.out.println("TimeFilter stop");
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter is destory");
    }
}
