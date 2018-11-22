package com.gongdixin.core.validatecode;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
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
    public void createValidateCode(ServletWebRequest request) throws Exception {

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
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
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
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }
}
