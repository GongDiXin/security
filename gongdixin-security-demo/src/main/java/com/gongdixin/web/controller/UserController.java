package com.gongdixin.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gongdixin.dto.User;
import com.gongdixin.dto.UserQueryCondition;
import com.gongdixin.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/16 22:25
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取当前登录用户信息
     *
     * @author GongDiXin
     * @date 2018/11/18 13:06
     * @param
     * @return
     * @exception
    */
    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    /**
     * @author GongDiXin
     * @date 2017/11/19 19:42
     * @description 获取用户信息
     * @param   name
     * @return List<User>
     * @throws
    */
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(@RequestParam(name = "username",required = false,defaultValue = "gongdixin") String name){
        System.out.println(name);
        return buildDomain();
    }

    /**
     * @author GongDiXin
     * @date 2017/11/19 19:42
     * @description 根据条件获取单个对象信息
     * @param pageable,condition
     * @return List<User>
     * @throws
    */
    @GetMapping("/userCondition")
    @JsonView(User.UserSimpleView.class)
    public List<User> queryUserWithCondition(UserQueryCondition condition, @PageableDefault(page = 2,size = 20,sort = "username,asc")Pageable pageable){
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        return buildDomain();
    }


    /**
     * @author GongDiXin
     * @date 2017/11/19 13:30
     * @description PathVariable注解是把url片段中申明的id的值 最为方法中id的值
     * @param
     * @return
     * @throws
    */

    /*
        通过JsonView来控制返回的数据 既返回不同的json串 对不同的查询做出返回区分 界面拿到的json串也就不同
        通过@GetMapping替换@RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET)
    */
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    @ApiOperation(value = "查询用户信息服务")
    public User getUserInfo(@ApiParam(value = "用户id") @PathVariable(name = "id") String id){
        User user = new User();
        user.setUsername("gongdixin");
        return user;
    }

    /**
     * @author GongDiXin
     * @date 2017/11/19 19:41
     * @description 新增用户
     * @param
     * @return
     * @throws
    */
    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        /*if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }*/
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    /**
     * @author GongDiXin
     * @date 2017/11/19 19:44
     * @description 修改用户信息
     * @return User
     * @throws
    */
    @PutMapping("/{id:\\d+}")
    public User updateUser(@Valid @RequestBody User user,BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error ->{
                    FieldError fieldError = (FieldError)error;
                    String message =fieldError.getField()+"  "+error.getDefaultMessage();
                    System.out.println(message);
                }
            );
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        System.out.println(user.getBirthday());
        user.setId("1");
        //StandardService
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteUser(@PathVariable int id){
        System.out.println(id);
    }

    /**
     * @author GongDiXin
     * @date 2017/11/20 21:19
     * @description 测试跳转500界面
     * @throws
    */
    @GetMapping("/error/{id:\\d+}")
    public void getServerErrorView(@PathVariable String id){
        throw new UserNotExistException(id);
    }

    /**
     * @author GongDiXin
     * @date 2017/11/20 21:19
     * @description 测试过滤器
     * @throws
     */
    @GetMapping("/filter/{id:\\d+}")
    public User getUserInfoWithFilter(@PathVariable String id){
        System.out.println("进入getUserInfoWithFilter服务");
        User user = new User();
        user.setUsername("gongdixin");
        return user;
    }

    @GetMapping("/aspect/{id:\\d+}")
    public User getUserInfoWithAspect(@PathVariable String id){
        System.out.println("进入getUserInfoWithAspect服务");
        User user = new User();
        user.setId(id);
        user.setUsername("gongdixin");
        return user;
    }

    /**
     * @author GongDiXin
     * @date 2017/11/19 19:41
     * @description 构建domain
     * @param
     * @return
     * @throws
    */
    private List<User> buildDomain(){
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return  users;
    }
}
