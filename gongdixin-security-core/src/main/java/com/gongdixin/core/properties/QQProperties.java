package com.gongdixin.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/12/13 23:05
 */
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
