package com.gysoft.jdbc.test.core;

import com.gysoft.jdbc.bean.*;
import org.springframework.stereotype.Component;

/**
 * @author zhouning
 * @date 2022/10/17 19:03
 */
@Component
public class SQLInterceptorImpl implements SQLInterceptor {
    @Override
    public void beforeBuild(SQLType sqlType, AbstractCriteria criteria) throws Exception {
        System.out.println("before build...");
        if(criteria !=null){
            if(criteria instanceof Criteria){
            }else{
                SQL sql = (SQL) criteria;
                System.out.println(sql.getUpdates());
            }
        }
    }

    @Override
    public void afterBuild(String sql, Object[] args) throws Exception {
        sql = "select * from DUAL";

    }
}
