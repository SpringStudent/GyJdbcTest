package com.gysoft.jdbc.test.service;

import com.gysoft.jdbc.multi.*;
import com.gysoft.jdbc.multi.balance.LeastActiveLoadBalance;
import com.gysoft.jdbc.multi.balance.RandomLoadBalance;
import com.gysoft.jdbc.test.dao.TbAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 周宁
 * @Date 2019-09-02 9:32
 */
@BindPoint(group = "slaveGroup", loadBalance = RandomLoadBalance.class)
public class TbAccountServiceImpl implements TbAccountService {

    private TbAccountDao tbAccountDao;

    @Autowired
    private JdbcRoutingDataSource jdbcRoutingDataSource;

    public void setTbAccountDao(TbAccountDao tbAccountDao) {
        this.tbAccountDao = tbAccountDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @BindPoint(group = "slaveGroup", loadBalance = RandomLoadBalance.class)
    public void bindDataSource() throws Exception {
        System.out.println("bindDataSource:"+jdbcRoutingDataSource.determineCurrentLookupKey());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @BindPoint(group = "slaveGroup", loadBalance = RandomLoadBalance.class)
    public void bindDataSource2() throws Exception {
        System.out.println("bindDataSource2:"+jdbcRoutingDataSource.determineCurrentLookupKey());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @BindPoint(group = "slaveGroup", loadBalance = RandomLoadBalance.class)
    public void bindDataSource3() throws Exception {
        System.out.println("bindDataSource3:"+jdbcRoutingDataSource.determineCurrentLookupKey());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @BindPoint(group = "slaveGroup")
    public void bindDataSource4() throws Exception {
        System.out.println("bindDataSource4:"+jdbcRoutingDataSource.determineCurrentLookupKey());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @BindPoint(group = "slaveGroup")
    public void bindDataSource5() throws Exception {
        System.out.println("bindDataSource5:"+jdbcRoutingDataSource.determineCurrentLookupKey());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @BindPoint(group = "slaveGroup")
    public void bindDataSource6() throws Exception {
        System.out.println("bindDataSource6:"+jdbcRoutingDataSource.determineCurrentLookupKey());
    }

    @Override
    public void bindDataSource7() throws Exception {
        System.out.println("bindDataSource7:"+jdbcRoutingDataSource.determineCurrentLookupKey());
        tbAccountDao.bindGroup("slaveGroup");
        System.out.println("bindDataSource7:"+jdbcRoutingDataSource.determineCurrentLookupKey());
        System.out.println("bindDataSource7:"+jdbcRoutingDataSource.determineCurrentLookupKey());
        System.out.println("bindDataSource7:"+jdbcRoutingDataSource.determineCurrentLookupKey());
    }

    @Override
    @BindPoint(key = "slave2")
    public void bindDataSource8() throws Exception {
        System.out.println("bindDataSource8:"+jdbcRoutingDataSource.determineCurrentLookupKey());
        System.out.println("bindDataSource8:"+jdbcRoutingDataSource.determineCurrentLookupKey());
        System.out.println("bindDataSource8:"+jdbcRoutingDataSource.determineCurrentLookupKey());
        //sql级别的数据源绑定执行过sql后，数据源回归到方法级别
        tbAccountDao.bindKey("master");
        System.out.println("bindDataSource8:"+jdbcRoutingDataSource.determineCurrentLookupKey());
        System.out.println("bindDataSource8:"+jdbcRoutingDataSource.determineCurrentLookupKey());

    }

    @Override
    @BindPoint(key = "slave2")
    public void bindDataSource9() throws Exception {
        System.out.println("bindDataSource9:"+jdbcRoutingDataSource.determineCurrentLookupKey());
    }

    @Override
    @BindPoint(group = "slaveGroup", loadBalance = LeastActiveLoadBalance.class)
    public void bindDataSource10() throws Exception {
        System.out.println("bindDataSource10:"+jdbcRoutingDataSource.determineCurrentLookupKey());
        tbAccountDao.bindKey("master");
        System.out.println("bindDataSource10:"+jdbcRoutingDataSource.determineCurrentLookupKey());
        tbAccountDao.bindGroup("slaveGroup",LeastActiveLoadBalance.class);
        System.out.println("bindDataSource10:"+jdbcRoutingDataSource.determineCurrentLookupKey());
    }
}
