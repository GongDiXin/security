package com.gongdixin.core.authentication.social.qq.api.impl;

import com.alibaba.fastjson.JSON;
import com.gongdixin.core.authentication.social.qq.api.QQ;
import com.gongdixin.core.authentication.social.qq.api.QQUserInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/12/11 22:59
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String  GET_USER_INFO_URL = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private static final String GET_OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 用户的ID，与QQ号码一一对应
     */
    private String openId;

    /**
     * 申请QQ登录成功后，分配给应用的appId
     */
    private String appId;

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(GET_OPENID_URL, accessToken);
        String retOpenId = getRestTemplate().getForObject(url, String.class);
        logger.info(String.format("QQImpl url: [%s] openId: [%s]", url, retOpenId));
        this.openId = StringUtils.substringBetween(retOpenId, "\"openid\":\"", "\"}");
    }

    /**
     * 获取qq用户信息
     *
     * @author GongDiXin
     * @date 2018/12/11 23:54
     * @param
     * @return
     * @exception
    */
    @Override
    public QQUserInfo getQQUserInfo() {
        String url = String.format(GET_USER_INFO_URL, appId, openId);
        String ret = getRestTemplate().getForObject(url, String.class);
        logger.info(String.format("getQQUserInfo url: [%s] ret: [%s]", url, ret));
        QQUserInfo info = null;
        try {
            info = JSON.parseObject(ret, QQUserInfo.class);
            info.setOpenId(openId);
        } catch (Exception e) {
            logger.error(String.format("getQQUserInfo result parse QQUserInfo error"));
        }
        return info;
    }
}
