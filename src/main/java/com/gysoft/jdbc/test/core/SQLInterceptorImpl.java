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
        if (sqlModifier.sqlId().equals("updateBirthAuto")) {
            sqlModifier.addUpdate("birth", new Date());
        } else if (sqlModifier.sqlId().equals("isActive1")) {
            sqlModifier.addAnd(Where.where("isActive").equal(1));
        }
    }

    @Override
    public void afterBuild(String sql, Object[] args) throws Exception {
        System.out.println("sql:" + sql + " args:" + ArrayUtils.toString(args));
    }
}
