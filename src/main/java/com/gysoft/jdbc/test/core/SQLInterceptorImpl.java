package com.gysoft.jdbc.test.core;

import com.gysoft.jdbc.bean.*;
import org.apache.commons.lang3.ArrayUtils;
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
    public void beforeBuild(SQLType sqlType, AbstractCriteria abstractCriteria) throws Exception {
        if (abstractCriteria != null) {
            System.out.print(sqlType+" ");
            if (sqlType.equals(SQLType.Select)) {
                abstractCriteria.where("1", 1);
            }
            if (abstractCriteria instanceof SQL) {
                SQL sql = (SQL) abstractCriteria;
                if (sqlType.equals(SQLType.Update) && sql.getTbName().equals("tb_user")) {
                    sql.set("birth", new Date());
                }
                if (sqlType.equals(SQLType.Insert) && sql.getTbName().equals("tb_account")) {
                    //修改插入参数
                    List<Object[]> values = sql.getInsertValues();
                    int i = 0;
                    for (Object[] arr : values) {
                        arr[1] = "username" + i;
                        i++;
                    }
                }
            }
        }
    }

    @Override
    public void afterBuild(String sql, Object[] args) throws Exception {
        System.out.println("sql=" + sql + " args=" + ArrayUtils.toString(args));
    }
}
