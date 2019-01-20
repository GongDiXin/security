package com.gongdixin.core.authentication.social.weixin.api;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2019/1/20 13:24
 */
public interface WeiXin {

    /**
     * 获取WeiXin用户信息
     *
     * @author GongDiXin
     * @date 2019/1/20 13:26
     * @param openId
    */
    WeiXinUserInfo getUserInfo(String openId);
}
