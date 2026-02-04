package com.gysoft.jdbc.test.bean;

import com.gysoft.jdbc.bean.SQLInterceptor;
import com.gysoft.jdbc.bean.SQLType;
import com.gysoft.jdbc.bean.SqlModifier;
import org.springframework.stereotype.Component;

//@Component
public class SqlInterceptorImpl implements SQLInterceptor {
    @Override
    public void beforeBuild(SQLType sqlType, SqlModifier sqlModifier) throws Exception {
        System.out.println("######### beforeBuild sqlType:" + sqlModifier.tableName());
    }

    @Override
    public void afterBuild(String sql, Object[] args) throws Exception {

    }
}
