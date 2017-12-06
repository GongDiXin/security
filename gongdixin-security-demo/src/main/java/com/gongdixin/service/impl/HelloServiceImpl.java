package com.gongdixin.service.impl;

import com.gongdixin.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/19 22:03
 * @description
 */
@Service
public class HelloServiceImpl implements HelloService{

    @Override
    public String greeting(String name) {
        return name;
    }
}
