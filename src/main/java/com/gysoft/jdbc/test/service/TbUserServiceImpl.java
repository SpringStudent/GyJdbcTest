package com.gysoft.jdbc.test.service;

import com.gysoft.jdbc.bean.Criteria;
import com.gysoft.jdbc.bean.PageResult;
import com.gysoft.jdbc.test.dao.TbUserDao;
import com.gysoft.jdbc.test.pojo.TbUser;
import com.gysoft.jdbc.tools.CustomResultSetExractorFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 周宁
 * @Date 2018-09-21 15:15
 */
public class TbUserServiceImpl implements TbUserService {

    @Resource
    private TbUserDao tbUserDao;

    @Override
    public void test() throws Exception {
       /*//查询所有用户
        List<TbUser> tbUsers = tbUserDao.queryAll();
        //根据主键查询一个用户
        TbUser tbUser = tbUserDao.queryOne(id);
        //根据用户名查询一个用户
        TbUser tbUser = tbUserDao.queryOne(new Criteria().where("name",name));
        //根据用户名批量查询用户
        List<TbUser> tbUsers = tbUserDao.queryWithCriteria(new Criteria().in("name",names));
        List<TbUser> tbUsers = tbUserDao.queryWithCriteria(new Criteria().where("name","in",names));
        //根据用户名和邮箱查询
        List<TbUser> tbUsers = tbUserDao.queryWithCriteria(new Criteria().in("name",names).and("email",email));
        //根据用户名模糊匹配
        List<TbUser> tbUsers = tbUserDao.queryWithCriteria(new Criteria().like("name",name));
        List<TbUser> tbUsers = tbUserDao.queryWithCriteria(new Criteria().where("name","like","%"+name));
        //用户名或者email满足条件
        List<TbUser> tbUsers = tbUserDao.queryWithCriteria(new Criteria().and("name",name).or("email",email));
        //id在ids列表+用户名和email同时满足条件结果集
        List<TbUser> tbUsers = tbUserDao.queryWithCriteria(new Criteria().in("id",ids).andCriteria(new Criteria().and("name",name).and("email",email)));
        //查询总人数
        Integer userCount = tbUserDao.queryIntegerWithCriteria(new Criteria().select("count(*)"));
        //查询每个邮箱的注册的人数
        Map<String,Object> emailNumMap = tbUserDao.queryMapsWithCriteria(new Criteria().select("count(*) as emailNum","email").groupBy("email"),CustomResultSetExractorFactory.createDoubleColumnValueResultSetExractor());
        //分页查询
        //select * from tb_user limit ?,?
        PageResult<TbUser> tbUserPageResult = tbUserDao.pageQuery(page);
        //select * from tb_user where birth < birth limit ?,?
        PageResult<TbUser> tbUserPageResult = tbUserDao.pageQueryWithCriteria(page,new Criteria().lt("birth",birth));
        //删除用户
        //delete from tb_user where id = id
        tbUserDao.delete(id);
        //条件删除用户
        //delete from tb_user where name in(names)
        tbUserDao.deleteWithCriteria(new Criteria().where("name","in",names));
        //更新一个用户
        //update tb_user set name = tbUser.name,email = tbUser.email,pwd = tbUser.pwd,birth=tbUser.birth where id = tbUser.id
        tbUserDao.update(tbUser);
        //批量更新用户
        tbUserDao.batchUpdate(tbUsers);*/
    }
}
