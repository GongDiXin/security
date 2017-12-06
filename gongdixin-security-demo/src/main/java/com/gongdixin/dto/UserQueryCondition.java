package com.gongdixin.dto;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2017/11/19 12:54
 * @description
 */
public class UserQueryCondition {
    private String username;
    private int age;
    private int ageTo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }
}
