package test;

import com.gysoft.jdbc.bean.Criteria;
import com.gysoft.jdbc.bean.Page;
import com.gysoft.jdbc.bean.PageResult;
import com.gysoft.jdbc.test.bean.SimpleUser;
import com.gysoft.jdbc.test.dao.TbUserDao;
import com.gysoft.jdbc.test.pojo.TbUser;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 周宁
 * @Date 2018-09-21 15:49
 */
public class TestGyJdbc {

    @Test
    public void testQuery() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbUserDao tbUserDao = (TbUserDao) ac.getBean("tbUserDao");
        //查询tbUser所有用户
        System.out.println(tbUserDao.queryAll());
        //查询tbUser某些字段
        List<Map<String,Object>> mapList = tbUserDao.queryMapsWithCriteria(new Criteria().select("name","email","birth"));
        List<SimpleUser> simpleUsers = mapList.stream().map(stringSimpleUserMap -> {
            SimpleUser simpleUser = new SimpleUser();
            try {
                BeanUtils.copyProperties(simpleUser,stringSimpleUserMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return simpleUser;
        }).collect(Collectors.toList());
        System.out.println(simpleUsers.get(0));
        //根据用户名查询
        System.out.println(tbUserDao.queryWithCriteria(new Criteria().in(TbUser::getName, Arrays.asList("zhouning","cyl"))));
        //分页查询
        PageResult<TbUser> pageResult = tbUserDao.pageQueryWithCriteria(new Page(1,10),new Criteria().lt(TbUser::getBirth,new Date()));
        System.out.println(pageResult);
        //复杂条件查询="SELECT * FROM tb_user where birth < now and (name like 'zhou' or email like '%qq.com%')"
        System.out.println(tbUserDao.queryWithCriteria(new Criteria().lt(TbUser::getBirth,new Date()).andCriteria(new Criteria().like(TbUser::getName,"zhou").or(TbUser::getEmail,"like","%@qq.com%"))));
    }

    @Test
    public void testUpdate() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbUserDao tbUserDao = (TbUserDao) ac.getBean("tbUserDao");
        int num = tbUserDao.updateWithCriteria(new Criteria().update(SimpleUser::getEmail,"33@qq.com")
                .update(SimpleUser::getBirth,new Date()).where(SimpleUser::getName,"zhouning"));
        System.out.print(num);
    }

    @Test
    public void testSql() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbUserDao tbUserDao = (TbUserDao) ac.getBean("tbUserDao");
        Integer count = new Criteria().useSql(Integer.class,"select count(*) from tb_user",tbUserDao).queryForObject();
        List<Map<String,Object>> mapList = new Criteria().useSql("select name,email from tb_user",tbUserDao).queryForMaps();
        PageResult<TbUser> tbUserPageResult = new Criteria().useSql(TbUser.class,"select * from tb_user",tbUserDao).pageQuery(new Page(1,1));
        PageResult<TbUser> tbUserPageResult12 = tbUserDao.pageQuery(new Page(1,1));
        System.out.println(count);
        System.out.print(mapList);
    }
}
