package com.gongdixin.core.properties;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/18 22:16
 */
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    /**
     * 验证码宽度
     */
    private int width = 67;

    /**
     * 验证码高度
     */
    private int height = 23;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
