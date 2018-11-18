package com.gongdixin.core.properties;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/18 22:16
 */
public class ImageCodeProperties {

    /**
     * 验证码宽度
     */
    private int width = 67;

    /**
     * 验证码高度
     */
    private int height = 23;

    /**
     * 验证码数字个数
     */
    private int length = 4;

    /**
     * 过期时间
     */
    private int expireIn = 60;

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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
}
