package com.gongdixin.core.validatecode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理
 *
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/22 22:43
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建验证码
     *
     * @author GongDiXin
     * @date 2018/11/22 22:47
     * @param request
     * @exception Exception
    */
    void createValidateCode(ServletWebRequest request) throws Exception;

}
