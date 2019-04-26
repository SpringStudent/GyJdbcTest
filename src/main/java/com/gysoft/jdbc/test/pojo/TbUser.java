package com.gysoft.jdbc.test.pojo;

import com.gysoft.jdbc.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * @author 周宁
 * @Date 2018-09-21 15:11
 */
@Table(name = "tb_user")
@Data
public class TbUser {
    private String id;
    private String name;
    private String realName;
    private String pwd;
    private String email;
    private String mobile;
    private Date birth;
    private Integer age;
    private String career;
    private Integer isActive = 0;
    private Integer roleId;
}
