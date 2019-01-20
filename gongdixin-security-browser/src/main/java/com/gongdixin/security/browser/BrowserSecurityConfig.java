package com.gongdixin.security.browser;

import com.gongdixin.core.authentication.AbstractChannelSecurityConfig;
import com.gongdixin.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.gongdixin.core.properties.SecurityConstants;
import com.gongdixin.core.properties.SecurityProperties;
import com.gongdixin.core.validatecode.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/7 21:53
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer socialSecurityConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

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

        applyPasswordAuthenticationConfig(http);

        // 表单登录 也可以设置成HttpBasic登录
        http.apply(validateCodeSecurityConfig).
                and().
            apply(smsCodeAuthenticationSecurityConfig).
                and().
            apply(socialSecurityConfig).
                and().
            rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                // 允许最大同时存在的session数量
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                // 超过session最大限制时是否允许登录
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
            .logout()
                .logoutUrl("/signOut")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
            // 授权
            .authorizeRequests()
            // 这个地方写文件的相对路径还不行
            .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getLoginPage(),
                        "/user/register",
                        SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL,
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        securityProperties.getBrowser().getSignOutUrl())
                        .permitAll()
            // 针对任何请求
            .anyRequest()
            // 认证 你是谁
            .authenticated()
            .and()
            // csrf跨站请求伪造
            .csrf().disable();
    }
}
