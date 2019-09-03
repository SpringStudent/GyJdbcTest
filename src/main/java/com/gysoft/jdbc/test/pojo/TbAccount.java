package com.gysoft.jdbc.test.pojo;

import com.gysoft.jdbc.annotation.Table;

/**
 * @author 周宁
 * @Date 2019-05-15 15:49
 */
@Table(name = "tb_account")
public class TbAccount {

    private Integer id;

    private String userName;

    private String realName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "TbAccount{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
