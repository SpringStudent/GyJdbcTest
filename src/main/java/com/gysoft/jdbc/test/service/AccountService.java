package com.gysoft.jdbc.test.service;

import com.gysoft.jdbc.multi.BindPoint;
import com.gysoft.jdbc.test.dao.TbAccountDao;

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

    public void bindDataSource() throws Exception {
        System.out.println("common query" + tbAccountDao.queryAll());
    }

    @BindPoint(group = "slaveGroup")
    public void bindDataSource2() throws Exception {
        System.out.println("common query" + tbAccountDao.queryAll());
        System.out.println("common query" + tbAccountDao.queryAll());
    }
}
