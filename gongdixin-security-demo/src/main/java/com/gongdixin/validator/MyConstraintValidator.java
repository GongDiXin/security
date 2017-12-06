package com.gongdixin.validator;

import com.gongdixin.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/19 21:56
 * @description 校验器 ConstraintValidator<MyConstraint,Object>
 *              第一个泛型指定验证的注解是哪一个 第二个泛型指定校验的数据类型
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object>{

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("init my validator");
    }

    @Override
    //value用于校验的值 context注解上下文  包含注解的值
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.greeting("gogndixin");
        System.out.println(value);
        return false;
    }
}
