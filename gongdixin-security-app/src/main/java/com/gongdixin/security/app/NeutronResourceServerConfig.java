package com.gongdixin.security.app;

import com.gongdixin.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.gongdixin.core.properties.SecurityConstants;
import com.gongdixin.core.properties.SecurityProperties;
import com.gongdixin.core.validatecode.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2019/1/21 22:22
 */
@Configuration
@EnableResourceServer
public class NeutronResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer socialSecurityConfig;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(successHandler)
                .failureHandler(failureHandler);

        // 表单登录 也可以设置成HttpBasic登录
        http.//apply(validateCodeSecurityConfig).
                //and().
                apply(smsCodeAuthenticationSecurityConfig).
                and().
                apply(socialSecurityConfig).
                and()
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
