package com.gysoft.jdbc.test.service;

import com.gysoft.jdbc.multi.BindPoint;
import com.gysoft.jdbc.test.dao.TbAccountDao;

/**
 * @author 周宁
 * @Date 2019-09-02 9:32
 */
public class TbAccountServiceImpl implements TbAccountService{

    private TbAccountDao tbAccountDao;

    public void setTbAccountDao(TbAccountDao tbAccountDao) {
        this.tbAccountDao = tbAccountDao;
    }

    @Override
    @BindPoint("master")
    public void bindDataSource() throws Exception {
        System.out.println("common query"+tbAccountDao.queryAll());
        System.out.println("Master query"+tbAccountDao.bindMaster().queryAll());
        System.out.println("Slave query"+tbAccountDao.bindSlave().queryAll());
        System.out.println("Slave2 query"+tbAccountDao.bindPoint("slave2").queryAll());
        System.out.println("common query"+tbAccountDao.queryAll());
    }
}
