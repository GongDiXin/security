package com.gongdixin.core.validatecode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 实现此接口的类命名需按CodeGenerator结尾
 *
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/20 22:11
 */
public interface ValidateCodeGenerator {

    /**
     * 图形验证码生成器
     *
     * @author GongDiXin
     * @date 2018/11/20 22:12
     * @param request
     * @return
    */
    ValidateCode generate(ServletWebRequest request);
}
