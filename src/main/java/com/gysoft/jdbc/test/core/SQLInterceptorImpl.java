package com.gysoft.jdbc.test.core;

import com.gysoft.jdbc.bean.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author zhouning
 * @date 2022/10/17 19:47
 */
@Component
public class SQLInterceptorImpl implements SQLInterceptor {

    @Override
    public void beforeBuild(SQLType sqlType, SqlModifier sqlModifier) throws Exception {
        //粗粒度 通过sql类型统一添加需要更新和插入的字段
        if (sqlType.equals(SQLType.Update)) {
            if (sqlModifier.tableName().startsWith("sys_tb_")) {
                sqlModifier.addUpdate("updateTime", new Date());
                sqlModifier.addUpdate("updateUser", "admin");
            }
        } else if (sqlType.equals(SQLType.Insert)) {
            if (sqlModifier.tableName().startsWith("sys_tb_")) {
                sqlModifier.addInsert("createTime", new Date());
                sqlModifier.addInsert("createUser", "admin");
                sqlModifier.addUpdate("updateTime", new Date());
                sqlModifier.addUpdate("updateUser", "admin");
            }
        }

        //精细粒度  根据sqlId添加相应的更新字段和查询条件
        if (sqlModifier.sqlId().equals("updateBirthAuto")) {
            sqlModifier.addUpdate("isActive", 0);
        } else if (sqlModifier.sqlId().equals("isDelete1")) {
            sqlModifier.addAnd(Where.where("isActive").equal(1));
        }
    }

    @Override
    public void afterBuild(String sql, Object[] args) throws Exception {
        //sql审计
        System.out.println("sql:" + sql + " args:" + ArrayUtils.toString(args));
    }
}
