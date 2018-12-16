package com.gongdixin.core.authentication.social.qq.connect;

import com.gongdixin.core.authentication.social.qq.api.QQ;
import com.gongdixin.core.authentication.social.qq.api.impl.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/12/12 0:11
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private static final String AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize";

    private static final String ACCESSTOKEN_URL = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, AUTHORIZE_URL, ACCESSTOKEN_URL));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
