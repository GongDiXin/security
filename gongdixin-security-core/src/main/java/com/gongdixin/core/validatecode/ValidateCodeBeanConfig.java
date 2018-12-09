package com.gongdixin.core.validatecode;

import com.gongdixin.core.validatecode.image.ImageCodeGenerator;
import com.gongdixin.core.validatecode.sms.DefaultSmsCodeSender;
import com.gongdixin.core.validatecode.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/20 22:29
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Bean
    public ValidateCodeGenerator imageCodeGenerator() {
        return new ImageCodeGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
