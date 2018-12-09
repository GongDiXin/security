package com.gongdixin.core.validatecode.image;

import com.gongdixin.core.validatecode.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/12/3 22:56
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        // 将图片写到浏览器
        ImageIO.write(imageCode.getImage(), "JPEG" ,request.getResponse().getOutputStream());
    }
}
