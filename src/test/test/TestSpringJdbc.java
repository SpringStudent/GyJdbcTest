package test;

import com.gysoft.jdbc.test.dao.TbUserDao;
import java.util.Arrays;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ZhouNing
 * @date 2020/7/20 14:46
 */
public class TestSpringJdbc {

    @Test
    public void test() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbUserDao tbUserDao = (TbUserDao) ac.getBean("tbUserDao");
        System.out.println(tbUserDao.queryUsersByNames(Arrays.asList("zhouning","chengyl")));
        System.out.println(tbUserDao.queryUserByNamesOrAge(Arrays.asList("zhouning","chengyl"),27));
    }
}
