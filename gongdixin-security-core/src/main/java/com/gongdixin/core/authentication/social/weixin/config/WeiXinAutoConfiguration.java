package com.gongdixin.core.authentication.social.weixin.config;

import com.gongdixin.core.authentication.social.weixin.connect.WeiXinConnectionFactory;
import com.gongdixin.core.properties.SecurityProperties;
import com.gongdixin.core.properties.WeiXinProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 微信登录配置
 * 检查配置文件 只有微信的appid配了的时候才去加载
 * 
 * @author Gongdixin
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "gongdixin.security.social.weixin", name = "app-id")
public class WeiXinAutoConfiguration extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		WeiXinProperties weixinConfig = securityProperties.getSocial().getWeixin();
		return new WeiXinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
				weixinConfig.getAppSecret());
	}
	
}
