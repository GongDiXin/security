package com.gongdixin.security.browser;

import com.alibaba.fastjson.JSON;
import com.gongdixin.core.properties.SecurityConstants;
import com.gongdixin.core.properties.SecurityProperties;
import com.gongdixin.security.browser.support.SimpleResponse;
import com.gongdixin.security.browser.support.SocialUserInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/11 22:08
 */
@RestController
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 当需要身份认证的时候跳转到这里
     *
     * @author GongDiXin
     * @date 2018/11/11 22:42
     * @param
     * @return
     * @exception
    */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String target = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求路径是：" + target);
            if (StringUtils.endsWithIgnoreCase(target, SecurityConstants.SUFFIX_HTML) ||
                    StringUtils.endsWithIgnoreCase(target, "js")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页面");
    }

    @GetMapping("/social/user")
    @ResponseBody
    public Object getSocialUserInfo(HttpServletRequest request, HttpServletResponse response) {
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        socialUserInfo.setProviderId(connection.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
        socialUserInfo.setNickname(connection.getDisplayName());
        socialUserInfo.setHeadImg(connection.getImageUrl());
        response.setContentType("application/json");
        return JSON.toJSON(socialUserInfo);
    }
}
