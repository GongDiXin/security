package com.gongdixin.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/7 21:53
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                formLogin() // 表单登录 也可以这只成HttpBasic登录
                .loginPage("")
                .and()
                .authorizeRequests()  // 授权
                .anyRequest() // 针对任何请求
                .authenticated(); //认证 你是谁
    }
}
