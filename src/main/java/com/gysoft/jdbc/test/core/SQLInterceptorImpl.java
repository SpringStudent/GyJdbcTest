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
        System.out.println(sqlModifier.sqlId());
    }

    @Override
    public void afterBuild(String sql, Object[] args) throws Exception {
        System.out.println("sql:" + sql + " args:" + ArrayUtils.toString(args));
    }
}
