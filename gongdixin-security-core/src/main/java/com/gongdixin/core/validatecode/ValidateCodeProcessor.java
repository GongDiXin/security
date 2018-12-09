package com.gongdixin.core.validatecode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理
 * 实现此接口的类命名需按ValidateCodeProcessor结尾
 *
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/22 22:43
 */
public interface ValidateCodeProcessor {

    /**
     * 创建验证码
     *
     * @author GongDiXin
     * @date 2018/11/22 22:47
     * @param request
     * @exception Exception
    */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);

}
