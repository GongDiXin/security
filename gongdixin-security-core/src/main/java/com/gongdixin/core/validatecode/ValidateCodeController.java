package com.gongdixin.core.validatecode;

import com.gongdixin.core.validatecode.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/18 20:55
 */
@RestController
public class ValidateCodeController {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    /**
     * 获取图片验证码
     *
     * @author GongDiXin
     * @date 2018/11/18 20:56
     * @param
    */
    @RequestMapping("/code/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode)imageCodeGenerator.generate(new ServletWebRequest(request));
        // 将验证码放入session中
        sessionStrategy.setAttribute(new ServletRequestAttributes(request), SESSION_KEY, imageCode);
        // 将图片写到浏览器
        ImageIO.write(imageCode.getImage(), "JPEG" ,response.getOutputStream());
    }

    /**
     * 短信验证码
     *
     * @author GongDiXin
     * @date 2018/11/18 20:56
     * @param
     */
    @RequestMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
        // 将验证码放入session中
        sessionStrategy.setAttribute(new ServletRequestAttributes(request), SESSION_KEY, smsCode);
        String mobileNum = ServletRequestUtils.getRequiredStringParameter(request, "mobileNum");
        smsCodeSender.send(mobileNum, smsCode.getCode());
    }
}
