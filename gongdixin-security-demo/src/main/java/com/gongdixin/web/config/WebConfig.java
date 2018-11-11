package com.gongdixin.web.config;

import com.gongdixin.web.filter.TimeFilter;
import com.gongdixin.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/20 23:12
 * @description 项目配置类
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(timeInterceptor);
    }

    /**
     * 拦截异步请求要在这里配置
     * */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // configurer.registerDeferredResultInterceptors();
        // configurer.registerCallableInterceptors();
        // 设置超时时间
        // configurer.setDefaultTimeout(1000);
        // 自行设置线程池替代spring自带的线程池 因为Spring自带的没有重用
        // configurer.setTaskExecutor();

    }

    /**
      SpringBoot项目不需要配置文件，所有当有的外部需要引进的类没有@Component注解的时候Spring是不会实例化该类的
      所有在SpringBoot中添加配置类，根据Spring4的建议使用@Configuration注解代替xml配置文件
     */
    //@Bean
    /**
     * 注册拦截器
     *
     * @author GongDiXin
     * @date 2018/11/5 23:19
     * @param
     * @return
     * @exception
    */
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filter.setFilter(timeFilter);
        //设置需要拦截的路径
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filter.setUrlPatterns(urls);
        return filter;
    }
}
