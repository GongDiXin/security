package com.gongdixin.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/16 21:17
 * @description springboot 测试用例类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    //伪造一个web程序
    @Autowired
    private WebApplicationContext wac;

    //伪造web的MVC请求 不会真正启动tomcat   Mock:  模拟的; 仿制的
    private MockMvc mockMvc;

    //调用测试方法之前去实例化一个mockMvc对象
    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccessful() throws Exception{
        /*
        模拟一个get请求
        get  构建一个get请求
        contentType 请求的编码格式
        andExpect status 期望返回的状态码  isOk代表成功 既http的200
        andExpect jsonPath 以集合的形式返回 长度为3
        */
        String result = mockMvc.perform(get("/user")
                .param("username","gongdixin")
               .contentType(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(3))
               .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenQuerySuccessfulByCondition() throws Exception{
        /*
        在mockMvc.perform方法中，提交的数据依然是json，
        所以以后如果用ajax在前台提交数据的话依然是没有后问题的 同样是以json传输
        比如如下格式 其中包括种数据传一下几种传输的方式
            1.@PathVariable  当使用@RequestMapping URI template 样式映射时， 即 someUrl/{paramId},
            这时的paramId可通过 @PathVariable注解绑定它传过来的值到方法的参数上。 用于处理地址栏数据传输
            2.@RequestParam 常用来处理简单类型的绑定，通过Request.getParameter() 获取的String可直接转换为简单类型的情况
            (String--> 简单类型的转换操作由ConversionService配置的转换器来完成)
            3.@RequestBody  该注解常用来处理Content-Type:
            不是application/x-www-form-urlencoded编码的内容，例如application/json, application/xml等
        $.ajax({
                type: "POST",
                url: "user/login/",
                async:false,
                data: {username:name, password:password},
                dataType: "json",
                success: function(data){
                    if(null != data && data.id != ""){
                        //如果直接跳转至jsp的话不行 因为WEB-INF下的jsp是访问不到的
                        //所以应该是跳转至控制器然后由控制器跳转
                        forwardFlag = true;
                    }
                },
                error : function () {
                    $.messager.alert('alert','Username or password is incorrect!');
                    login.clearForm();

                }
            });
        */
        mockMvc.perform(get("/user/userCondition")
                .param("username","gongdixin")
                .param("age","25")
                .param("ageTo","60")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                //github 搜索jsonPath 第一个项目 有表达式的介绍
                .andExpect(jsonPath("$.length()").value(3));
    }

    /**
     * @author GongDiXin
     * @date 2017/11/19 13:24
     * @description 发送请求 并设置返回状态的状态码及请求编码
     * @param
     * @return
     * @throws
    */
    @Test
    public void whenGetInfoSuccess() throws  Exception{
        String result = mockMvc.perform(get("/user/1")
               .contentType(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.username").value("gongdixin"))
               .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * @author GongDiXin
     * @date 2017/11/19 13:46
     * @description 测试正则表达式
     * @param
     * @return
     * @throws
    */
    @Test
    public void whenGetInfoFail() throws Exception{
        mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception{
        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"username\":\"gongdixin\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        String result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);

    }

    @Test
    public void whenUpdateSuccess() throws Exception{
        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(date.getTime());
        String content = "{\"id\":\"1\",\"username\":\"gongdixin\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        String result = mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);

    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

}
