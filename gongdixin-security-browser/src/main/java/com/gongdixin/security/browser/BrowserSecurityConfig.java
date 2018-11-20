package com.gongdixin.security.browser;

import com.gongdixin.core.properties.SecurityProperties;
import com.gongdixin.core.validatecode.ValidateCodeFilter;
import com.gongdixin.security.browser.authentication.SecurityAuthenticationFailureHandler;
import com.gongdixin.security.browser.authentication.SecurityAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/7 21:53
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SecurityAuthenticationSuccessHandler securityAuthenticationSuccessHandler;

    @Autowired
    private SecurityAuthenticationFailureHandler securityAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    /**
     * 配置数据源和token仓库
     * 注意：@Bean注解一定要加
     *
     * @author GongDiXin
     * @date 2018/11/20 23:50
     * @param
     * @return
     * @exception
    */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 第一次启动的时候Spring回去帮我们新建token表 但是第二次登陆的时候就要注释掉
        // 以后可以把JdbcTokenRepositoryImpl源码的sql写到初始化sql中
        // tokenRepository.setCreateTableOnStartup(true)
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter filter = new ValidateCodeFilter();
        filter.setAuthenticationFailureHandler(securityAuthenticationFailureHandler);
        filter.setSecurityProperties(securityProperties);
        filter.afterPropertiesSet();

        // 表单登录 也可以这只成HttpBasic登录
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).
            formLogin()
                // 登录界面
                .loginPage("/authentication/require")
                // authentication/form这个表单提交的地址是固定的
                .loginProcessingUrl("/authentication/form")
                .successHandler(securityAuthenticationSuccessHandler)
                .failureHandler(securityAuthenticationFailureHandler)
                .and()
            .rememberMe().tokenRepository(persistentTokenRepository())
            .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
            .userDetailsService(userDetailsService).and()
            // 授权
            .authorizeRequests()
            // 这个地方写文件的相对路径还不行
            .antMatchers("/authentication/require",
                    "/code/image",
                    securityProperties.getBrowser().getLoginPage()).permitAll()
            // 针对任何请求
            .anyRequest()
            // 认证 你是谁
            .authenticated()
            .and()
            // csrf跨站请求伪造
            .csrf().disable();
    }
}
