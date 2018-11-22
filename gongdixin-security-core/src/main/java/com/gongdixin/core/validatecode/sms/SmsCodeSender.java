package com.gongdixin.core.validatecode.sms;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/22 21:55
 */
public interface SmsCodeSender {

    /**
     * 发送短信服务(后期可集成第三方的短信接口)
     *
     * @author GongDiXin
     * @date 2018/11/22 21:56
     * @param mobileNum
     * @param code
    */
    void send(String mobileNum, String code);
}
