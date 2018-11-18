package com.gongdixin.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/11 22:30
 */
@ConfigurationProperties(prefix = "gongdixin.security")
public class SecurityProperties {

    /**
     * 这里需要初始化，Spring Boot没有做注入
     */
    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
