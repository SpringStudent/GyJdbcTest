package com.gysoft.jdbc.test.service;

import com.gysoft.jdbc.multi.BindPoint;
import com.gysoft.jdbc.multi.DataSourceContext;
import com.gysoft.jdbc.test.dao.TbAccountDao;

import java.util.function.Supplier;

/**
 * @author 周宁
 * @Date 2019-09-04 11:28
 */
@BindPoint(key = "slave2")
public class AccountService {
    private TbAccountDao tbAccountDao;

    public void setTbAccountDao(TbAccountDao tbAccountDao) {
        this.tbAccountDao = tbAccountDao;
    }

    @BindPoint(key = "master")
    public void bindDataSource() throws Exception {
        System.out.println("common query1" + tbAccountDao.queryAll());
        DataSourceContext.withDataSource("slave", ()->{
            System.out.println("common query2" + tbAccountDao.queryAll());
        });
        System.out.println("common query3" + tbAccountDao.queryAll());
        System.out.println("common query4" + tbAccountDao.bindKey("slave2").queryAll());
    }

    @BindPoint(group = "slaveGroup")
    public void bindDataSource2() throws Exception {
        System.out.println("common query5" + tbAccountDao.queryAll());
        System.out.println("common query6" + tbAccountDao.queryAll());
    }

}
