package com.gongdixin.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 授权配置管理
 *
 * @author GongDiXin
 * @version 1.0
 * @created 2019/1/23 22:21
 */
public interface AuthorizeConfigManager {

    /**
     * 授权配置
     *
     * @author GongDiXin
     * @date 2019/1/23 22:12
     * @param config
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
