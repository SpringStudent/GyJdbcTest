package com.gysoft.jdbc.test.service;

import com.gysoft.jdbc.bean.*;
import com.gysoft.jdbc.test.bean.SimpleUser;
import com.gysoft.jdbc.test.dao.TbUserDao;
import com.gysoft.jdbc.test.pojo.TbAccount;
import com.gysoft.jdbc.test.pojo.TbUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.gysoft.jdbc.bean.FuncBuilder.count;

/**
 * @author 周宁
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Resource
    private TbUserDao tbUserDao;

    @Override
    public void test() throws Exception {
        //SELECT * FROM tb_user where name in('zhouning','yinhw');
        List<TbUser> tbUsers = tbUserDao.bindKey("slave").queryWithCriteria(new Criteria().in(TbUser::getName, Arrays.asList("zhouning", "yinhw")));
        //SELECT name, email, realName, mobile FROM tb_user WHERE isActive = 1
        SQL sql = new SQL().select(TbUser::getName, TbUser::getEmail, TbUser::getRealName, TbUser::getMobile)
                .from(TbUser.class).where(TbUser::getIsActive, 1);
        List<SimpleUser> simpleUsers = tbUserDao.queryWithSql(SimpleUser.class, sql).queryList();
        //SELECT name, email, realName, mobile FROM tb_user WHERE isActive = 1 limit 0,2
        SQL sql2 = new SQL().select(TbUser::getName, TbUser::getEmail, TbUser::getRealName, TbUser::getMobile)
                .from(TbUser.class).where(TbUser::getIsActive, 1);
        PageResult<SimpleUser> simpleUsers2 = tbUserDao.queryWithSql(SimpleUser.class, sql2).pageQuery(new Page(1, 2));
        //SELECT count(id) from tb_user
        SQL sql3 = new SQL().select(count(TbUser::getId)).from(TbUser.class);
        Integer count = tbUserDao.queryIntegerWithSql(sql3);

        //UPDATE tb_user set realName = "元林",email = "13888888888@163.com" WHERE name = "Smith"
        tbUserDao.bindGroup("masterGroup").updateWithSql(new SQL().update(TbUser.class).set(TbUser::getRealName, "元林").set(TbUser::getEmail, "13888888888@163.com").where(TbUser::getName, "Smith"));
        TbUser tbUser = new TbUser();
        tbUserDao.update(tbUser);

        //DELTE FROM tb_user where id = "id"
        tbUserDao.delete("id");
        //DELTE FROm tb_user where userName in ("test2");
        tbUserDao.deleteWithCriteria(new Criteria().in("userName", Arrays.asList("test2")));
    }

}
