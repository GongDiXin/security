package com.gongdixin.core.properties;

/**
 * @author GongDiXin
 * @version 1.0
 * Created by Administrator on 2018/11/11.
 */
public class BrowserProperties {

    private String loginPage = "/signIn_default.html";

    private LoginType loginType = LoginType.JSON;

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
}
