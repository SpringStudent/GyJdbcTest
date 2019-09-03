package com.gysoft.jdbc.test.pojo;

import com.gysoft.jdbc.annotation.Table;

/**
 * @author 周宁
 * @Date 2019-04-26 16:02
 */
@Table(name = "tb_role")
public class TbRole {

    private String id;

    private String roleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
