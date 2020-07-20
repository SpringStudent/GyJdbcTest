package com.gysoft.jdbc.test.dao;

import com.gysoft.jdbc.dao.EntityDaoImpl;
import com.gysoft.jdbc.dao.ISpringJdbc;
import com.gysoft.jdbc.test.pojo.TbUser;
import java.util.List;
import javax.annotation.Resource;

/**
 * @author 周宁
 * @Date 2018-09-21 15:14
 */
public class TbUserDaoImpl extends EntityDaoImpl<TbUser,String> implements TbUserDao {

    private ISpringJdbc springJdbc;

    public ISpringJdbc getSpringJdbc() {
        return springJdbc;
    }

    public void setSpringJdbc(ISpringJdbc springJdbc) {
        this.springJdbc = springJdbc;
    }

    @Override
    public List<TbUser> queryUsersByNames(List<String> names) throws Exception {
        return springJdbc.query("SELECT * FROM tb_user WHERE name in(?)",new Object[]{names},TbUser.class);
    }

    @Override
    public List<TbUser> queryUserByNamesOrAge(List<String> names, Integer age) throws Exception {
        return springJdbc.query("SELECT * FROM tb_user WHERE name in(?) OR age > ?",new Object[]{names,age},TbUser.class);

    }
}
