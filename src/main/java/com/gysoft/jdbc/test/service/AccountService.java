package com.gysoft.jdbc.test.service;

import com.gysoft.jdbc.multi.BindPoint;
import com.gysoft.jdbc.multi.DataSourceContext;
import com.gysoft.jdbc.multi.balance.LeastActiveLoadBalance;
import com.gysoft.jdbc.multi.balance.RandomLoadBalance;
import com.gysoft.jdbc.multi.balance.SelectFirstLoadBalance;
import com.gysoft.jdbc.multi.balance.SelectLastLoadBalance;
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

    @BindPoint(group = "slaveGroup",loadBalance = RandomLoadBalance.class)
    public void bindDataSource() throws Exception {
        System.out.println("common query1" + tbAccountDao.queryAll());
        DataSourceContext.withDataSource("slave", ()->{
            System.out.println("common query2" + tbAccountDao.queryAll());
        });
        System.out.println("common query3" + tbAccountDao.queryAll());
        System.out.println("common query4" + tbAccountDao.bindKey("master").queryAll());
    }

    @BindPoint(group = "slaveGroup",loadBalance = LeastActiveLoadBalance.class)
    public void bindDataSource2() throws Exception {
        System.out.println("common query5" + tbAccountDao.queryAll());
        System.out.println("common query6" + tbAccountDao.queryAll());
    }

}
