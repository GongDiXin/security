package com.gongdixin.core;

import com.gongdixin.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/11 22:36
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
