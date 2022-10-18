package com.gysoft.jdbc.test.core;

import com.gysoft.jdbc.bean.AbstractCriteria;
import com.gysoft.jdbc.bean.SQLInterceptor;
import com.gysoft.jdbc.bean.SQLType;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Component;

/**
 * @author zhouning
 * @date 2022/10/17 19:47
 */
@Component
public class SQLInterceptorImpl2 implements SQLInterceptor {
    @Override
    public void beforeBuild(SQLType sqlType, AbstractCriteria criteria) throws Exception {

    }

    @Override
    public void afterBuild(String sql, Object[] args) throws Exception {
        System.out.println("sql="+sql);
        System.out.println("args="+ ArrayUtils.toString(args));
    }
}
