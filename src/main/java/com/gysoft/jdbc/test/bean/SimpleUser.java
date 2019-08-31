package com.gysoft.jdbc.test.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author 周宁
 * @Date 2018-09-21 17:15
 */
@Data
public class SimpleUser {

    private String name;

    private String realName;

    private String email;

    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
