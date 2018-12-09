package com.gongdixin.core.validatecode.sms;

import com.gongdixin.core.properties.SecurityProperties;
import com.gongdixin.core.validatecode.ValidateCode;
import com.gongdixin.core.validatecode.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/22 22:20
 */
@Component
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }
}
