package com.gongdixin.core.properties;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/22 22:24
 */
public class SmsCodeProperties {

    /**
     * 验证码数字个数
     */
    private int length = 6;

    /**
     * 过期时间
     */
    private int expireIn = 60;

    /**
     * 验证码校验拦截路径
     */
    private String url;


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
