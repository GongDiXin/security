package com.gongdixin.core.properties;

/**
 * @author GongDiXin
 * @version 1.0
 * Created by Administrator on 2018/11/11.
 */
public class BrowserProperties {

    private String loginPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;

    private LoginType loginType = LoginType.JSON;

    /**
     * 记住我过期时间
     */
    private int rememberMeSeconds = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
