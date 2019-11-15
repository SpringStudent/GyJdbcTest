package com.gysoft.jdbc.test.service;

import com.gysoft.jdbc.multi.*;
import com.gysoft.jdbc.test.dao.TbAccountDao;

/**
 * @author 周宁
 * @Date 2019-09-02 9:32
 */
@BindPoint(group = "slaveGroup",loadBalance = RandomLoadBalance.class)
public class TbAccountServiceImpl implements TbAccountService{

    private TbAccountDao tbAccountDao;

    public void setTbAccountDao(TbAccountDao tbAccountDao) {
        this.tbAccountDao = tbAccountDao;
    }

    @Override
    @BindPoint(group = "slaveGroup",loadBalance = RandomLoadBalance.class)
    public void bindDataSource() throws Exception {
        System.out.println(DataSourceBindHolder.getDataSource());
    }

    @Override
    @BindPoint(group = "slaveGroup",loadBalance = RandomLoadBalance.class)
    public void bindDataSource2() throws Exception {
        System.out.println(DataSourceBindHolder.getDataSource());
    }

    @Override
    @BindPoint(group = "slaveGroup",loadBalance = RandomLoadBalance.class)
    public void bindDataSource3() throws Exception {
        System.out.println(DataSourceBindHolder.getDataSource());
    }

    @Override
    @BindPoint(group = "slaveGroup")
    public void bindDataSource4() throws Exception {
        System.out.println(DataSourceBindHolder.getDataSource());
    }

    @Override
    @BindPoint(group = "slaveGroup")
    public void bindDataSource5() throws Exception {
        System.out.println(DataSourceBindHolder.getDataSource());
    }

    @Override
    @BindPoint(group = "slaveGroup")
    public void bindDataSource6() throws Exception {
        System.out.println(DataSourceBindHolder.getDataSource());
    }

    @Override
    public void bindDataSource7() throws Exception {
        System.out.println(DataSourceBindHolder.getDataSource());
        tbAccountDao.bindGroup("slaveGroup");
        System.out.println(DataSourceBindHolder.getDataSource());
        System.out.println(DataSourceBindHolder.getDataSource());
        System.out.println(DataSourceBindHolder.getDataSource());
    }

    @Override
    @BindPoint(key = "slave9")
    public void bindDataSource8() throws Exception {
        System.out.println(DataSourceBindHolder.getDataSource());
        System.out.println(DataSourceBindHolder.getDataSource());
        System.out.println(DataSourceBindHolder.getDataSource());
        tbAccountDao.bindKey("master");
        System.out.println(DataSourceBindHolder.getDataSource());
        System.out.println(DataSourceBindHolder.getDataSource());

    }
}
