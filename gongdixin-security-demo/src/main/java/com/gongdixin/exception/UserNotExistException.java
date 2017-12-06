package com.gongdixin.exception;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/20 21:26
 * @description
 */
public class UserNotExistException extends RuntimeException{

    private static final long serialVersionUID = -4582088121066152483L;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserNotExistException(String id){
        //调用父类构造函数 传递错误信息
        super("user not exist");
        this.id = id;
    }
}
