package com.gongdixin.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 授权配置类 如要实现自定义配置类 需实现该接口
 *
 * @author GongDiXin
 * @version 1.0
 * @created 2019/1/23 22:11
 */
public interface AuthorizeConfigProvider {

    /**
     * 授权配置
     *
     * @author GongDiXin
     * @date 2019/1/23 22:12
     * @param config
    */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
