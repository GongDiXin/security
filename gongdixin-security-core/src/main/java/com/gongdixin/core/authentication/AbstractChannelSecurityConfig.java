package com.gongdixin.core.authentication;

import com.gongdixin.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/12/9 18:43
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler successHandlerHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
            .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
            .successHandler(successHandlerHandler)
            .failureHandler(failureHandler);
    }
}
