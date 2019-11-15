package com.gysoft.jdbc.test.service;

import com.gysoft.jdbc.multi.BindPoint;
import com.gysoft.jdbc.test.dao.TbAccountDao;

/**
 * @author 周宁
 * @Date 2019-09-04 11:28
 */

public class AccountService {
    private TbAccountDao tbAccountDao;

    public void setTbAccountDao(TbAccountDao tbAccountDao) {
        this.tbAccountDao = tbAccountDao;
    }

    @BindPoint(key = "slave")
    public void bindDataSource() throws Exception {
        System.out.println("common query" + tbAccountDao.queryAll());
    }
}
