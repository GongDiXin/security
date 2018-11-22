package com.gongdixin.core.validatecode.sms;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/22 21:57
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobileNum, String code) {
        System.out.println("向手机: " + mobileNum + " 发送验证码" + code);
    }
}
