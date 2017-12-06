package com.gongdixin.web.async;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/28 22:35
 * @description
 */
@RestController
public class DeferredResultHolder {

    private Map<String,DeferredResult<String>> map = new HashMap<>();

    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<String>> map) {
        this.map = map;
    }
}
