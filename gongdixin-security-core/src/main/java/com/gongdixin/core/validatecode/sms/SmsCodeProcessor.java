package com.gongdixin.core.validatecode.sms;

import com.gongdixin.core.validatecode.AbstractValidateCodeProcessor;
import com.gongdixin.core.validatecode.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/12/3 22:57
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobileNum = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobileNum");
        smsCodeSender.send(mobileNum, validateCode.getCode());
    }
}
