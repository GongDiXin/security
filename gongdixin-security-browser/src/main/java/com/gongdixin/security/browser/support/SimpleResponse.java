package com.gongdixin.security.browser.support;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/11/11 22:18
 */
public class SimpleResponse {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public SimpleResponse(Object data) {
        this.data = data;
    }
}
