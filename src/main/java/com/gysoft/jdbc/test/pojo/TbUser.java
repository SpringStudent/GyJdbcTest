package com.gysoft.jdbc.test.pojo;

import com.gysoft.jdbc.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * @author 周宁
 * @Date 2018-09-21 15:11
 */
@Table(name = "tb_user",pk = "id")
@Data
public class TbUser {
    private Integer id;
    private String name;
    private String pwd;
    private String email;
    private Date birth;
}
