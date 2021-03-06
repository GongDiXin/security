/**
 * 
 */
package com.gongdixin.core.authentication.social.weixin.api.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gongdixin.core.authentication.social.weixin.api.WeiXin;
import com.gongdixin.core.authentication.social.weixin.api.WeiXinUserInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

/**
 * WeiXin API调用模板， scope为Request的Spring bean, 根据当前用户的accessToken创建。
 * @author GongDiXin
 * @date 2019/1/20 13:26
 */
public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeiXin {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String WEIXIN_ERROR_CODE = "errcode";
	/**
	 * 获取用户信息的url
	 */
	private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

	/**
	 * @param accessToken
	 */
	public WeiXinImpl(String accessToken) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
	}
	
	/**
	 * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
	 */
	@Override
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
		messageConverters.remove(0);
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return messageConverters;
	}

	/**
	 * 获取微信用户信息。
	 */
	@Override
	public WeiXinUserInfo getUserInfo(String openId) {
		String url = URL_GET_USER_INFO + openId;
		String response = getRestTemplate().getForObject(url, String.class);
		if(StringUtils.contains(response, WEIXIN_ERROR_CODE)) {
			return null;
		}
		WeiXinUserInfo profile = null;
		try {
			profile = JSON.parseObject(response, WeiXinUserInfo.class);
		} catch (Exception e) {
			logger.error("WeiXinImpl getUserInfo resources.error, response: " + response);
		}
		return profile;
	}

}
