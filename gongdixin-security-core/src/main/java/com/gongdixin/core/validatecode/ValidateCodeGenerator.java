package com.gongdixin.core.validatecode;

import org.springframework.web.context.request.ServletWebRequest;

/**
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
     * @param
     * @return
     * @exception
    */
    ImageCode generate(ServletWebRequest request);
}
