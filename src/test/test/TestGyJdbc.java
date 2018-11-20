package test;

import com.gysoft.jdbc.bean.Criteria;
import com.gysoft.jdbc.test.dao.TbUserDao;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
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
        System.out.println(tbUserDao.queryAll().size());
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
    }

    @Test
    public void testUpdate() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbUserDao tbUserDao = (TbUserDao) ac.getBean("tbUserDao");
        int num = tbUserDao.updateWithCriteria(new Criteria().update(SimpleUser::getEmail,"33@qq.com")
                .update(SimpleUser::getBirth,new Date()).where(SimpleUser::getName,"zhouning"));
        System.out.println(num);
    }

}
