package com.gysoft.jdbc.test.dao;

import com.gysoft.jdbc.dao.EntityDao;
import com.gysoft.jdbc.test.pojo.TbUser;
import java.util.List;

/**
 * @author 周宁
 * @Date 2018-09-21 15:13
 */

public interface TbUserDao extends EntityDao<TbUser,String> {

    List<TbUser> queryUsersByNames(List<String> names) throws Exception;

    List<TbUser> queryUserByNamesOrAge(List<String> names,Integer age)throws Exception;
}
