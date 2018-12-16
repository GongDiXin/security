package com.gongdixin.core.authentication.social.qq.connect;

import com.gongdixin.core.authentication.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/12/13 22:31
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
