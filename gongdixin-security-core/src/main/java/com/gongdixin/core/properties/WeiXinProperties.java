package com.gongdixin.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * 微信登录属性
 *
 * @author GongDiXin
 * @version 1.0
 * @created 2019/1/20 13:19
 */
public class WeiXinProperties extends SocialProperties {

    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 weixin。
     */
    private String providerId = "weixin";

    /**
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * @param providerId the providerId to set
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

}
