package com.gongdixin.security.browser;

import com.gongdixin.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单登录 也可以这只成HttpBasic登录
        http.formLogin()
                // 登录界面
                .loginPage("/authentication/require")
                // authentication/form这个表单提交的地址是固定的
                .loginProcessingUrl("/authentication/form")
                .and()
                // 授权
                .authorizeRequests()
                // 这个地方写文件的相对路径还不行
                .antMatchers("/authentication/require",
                        securityProperties.getBrowserProperties().getLoginPage()).permitAll()
                // 针对任何请求
                .anyRequest()
                // 认证 你是谁
                .authenticated()
                .and()
                // csrf跨站请求伪造
                .csrf().disable();
    }
}
