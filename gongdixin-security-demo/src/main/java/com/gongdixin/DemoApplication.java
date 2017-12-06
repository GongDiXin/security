package com.gongdixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/16 10:24
 * @description
 */
@SpringBootApplication
@RestController
@EnableSwagger2
public class DemoApplication {

    /**
     * @author GongDiXin
     * @date 2017/11/16 10:25
     * @description
     * @param
     * @return
     * @throws
    */
    public static void main(String[] args){
        SpringApplication.run(DemoApplication.class,args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }
}
