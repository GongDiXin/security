package com.gongdixin.core.authentication.social.qq.config;

import com.gongdixin.core.authentication.social.qq.connect.QQConnectionFactory;
import com.gongdixin.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/12/13 23:12
 */
@Configuration
@ConditionalOnProperty(prefix = "gongdixin.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new QQConnectionFactory(securityProperties.getSocial().getQq().getProviderId(),
                                        securityProperties.getSocial().getQq().getAppId(),
                                        securityProperties.getSocial().getQq().getAppSecret());
    }
}
