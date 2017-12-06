package com.gongdixin.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.gongdixin.validator.MyConstraint;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/16 22:36
 * @description
 */
public class User {

    /*
        JsonView使用步骤
        1.使用接口来声明多个视图
        2.在值对象的get方法上指定视图
        3.在Controller方法上制定视图
    */
    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{};

    private String id;
    @MyConstraint(message = "测试自定义注解")
    @ApiModelProperty(value = "用户名")
    private String username;


    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;


    @Past(message = "输入日期不能大于当前日期")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
