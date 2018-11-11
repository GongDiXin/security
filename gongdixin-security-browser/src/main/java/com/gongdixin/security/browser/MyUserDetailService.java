package com.gongdixin.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/8 22:14
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 自己的用户查询逻辑 后期自己加 相当于一个普通的Service层 可以注入Mapper
        logger.info("登录用户名" + username);
        // 根据查找到的用户信息判断用户是否过期
        // 实现UserDetails的另外四个接口方法
        // 使用User对象的另一个构造函数
        // 这里可以自行实现UserDetails 代替这里的User
        String password = passwordEncoder.encode("123456");
        logger.info("数据库用户密码是：" + password);
        return new User(username, password,
                true, true, true,true,
                 AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
