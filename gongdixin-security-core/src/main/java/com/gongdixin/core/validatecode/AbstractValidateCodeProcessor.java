package com.gongdixin.core.validatecode;

import com.gongdixin.core.properties.SecurityConstants;
import com.gongdixin.core.validatecode.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/22 22:50
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {
    /**
     * 操作session工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 收集所有实现了{@link ValidateCodeGenerator} 接口的实现
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;


    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 发送校验码
     *
     * @author GongDiXin
     * @date 2018/11/22 22:57
     * @param
     * @return
     * @exception
    */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 生成验证码
     *
     * @author GongDiXin
     * @date 2018/11/22 22:59
     * @param
     * @return
     * @exception
    */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator generator = validateCodeGenerators.get(type + "CodeGenerator");
        return (C) generator.generate(request);
    }

    /**
     * 保存验证码信息
     *
     * @author GongDiXin
     * @date 2018/11/22 23:09
     * @param
     * @return
     * @exception
    */
    private void save(ServletWebRequest request, C validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        sessionStrategy.setAttribute(request, getSessionKey(request).toUpperCase(), code);
    }

    /**
     * 根据请求地址获取校验码的类型
     *
     * @author GongDiXin
     * @date 2018/11/22 23:04
     * @param
     * @return
     * @exception
    */
    private String getSessionKey(ServletWebRequest request) {
        String validateType = StringUtils.substringAfter(request.getRequest().getRequestURI(),
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/");
        String ret;
        if (ValidateCodeType.IMAGE.name().equals(validateType.toUpperCase())) {
            ret = SecurityConstants.SESSION_KEY_FOR_CODE_IMAGE;
        } else if (ValidateCodeType.SMS.name().equals(validateType.toUpperCase())) {
            ret = SecurityConstants.SESSION_KEY_FOR_CODE_SMS;
        } else {
            ret = "";
        }
        return ret;
    }

    /**
     * 根据请求地址获取校验码的类型
     *
     * @author GongDiXin
     * @date 2018/11/22 23:04
     * @param
     * @return
     * @exception
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType validateCodeType = getValidateCodeType(request);
        ValidateCode codeInSession = null;
        String key;
        if (SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE.equals(validateCodeType.getParamNameOnValidate())) {
            codeInSession  = (ValidateCode) sessionStrategy.getAttribute(request, SecurityConstants.SESSION_KEY_FOR_CODE_IMAGE);
            key = SecurityConstants.SESSION_KEY_FOR_CODE_IMAGE;
        } else if (SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS.equals(validateCodeType.getParamNameOnValidate())) {
            codeInSession  = (ValidateCode) sessionStrategy.getAttribute(request, SecurityConstants.SESSION_KEY_FOR_CODE_SMS);
            key = SecurityConstants.SESSION_KEY_FOR_CODE_SMS;
        } else {
            key = "";
        }

        if (StringUtils.isBlank(key)) {
            throw new ValidateCodeException("未获取到session key");
        }

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), validateCodeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, key);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, key);
    }


    /**
     * 根据请求的url获取校验码的类型
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }
}
