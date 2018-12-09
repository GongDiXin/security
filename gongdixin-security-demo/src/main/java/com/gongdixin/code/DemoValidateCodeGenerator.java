package com.gongdixin.code;

import com.gongdixin.core.validatecode.image.ImageCode;
import com.gongdixin.core.validatecode.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/20 22:45
 */
// 这里注释掉 如果有需要替换的图形验证码生成器可以写在这里
//@Component("imageCodeGenerator")
public class DemoValidateCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码");
        return null;
    }
}
