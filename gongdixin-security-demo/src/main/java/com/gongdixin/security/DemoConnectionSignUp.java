package com.gongdixin.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 该类用于在社交登录绑定用户至业务系统中
 * 生成业务系统userId用于和社交网站用户openId和providerId绑定
 * connection封装了社交用户的信息
 *
 * @author GongDiXin
 * @version 1.0
 * @created 2018/12/17 23:01
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        return connection.getDisplayName();
    }
}
