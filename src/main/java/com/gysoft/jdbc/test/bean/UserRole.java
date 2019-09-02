package com.gysoft.jdbc.test.bean;

import lombok.Data;

/**
 * @author 周宁
 * @Date 2019-04-26 16:03
 */
@Data
public class UserRole {

    private String name;

    private String realName;

    private String roleId;

    private String roleName;

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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
