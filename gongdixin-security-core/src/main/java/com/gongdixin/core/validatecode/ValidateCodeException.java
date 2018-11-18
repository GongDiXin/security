package com.gongdixin.core.validatecode;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码处理异常类
 * AuthenticationException是SpringSecurity中所有认证异常的基类
 *
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/18 21:32
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -1127434803791276700L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
