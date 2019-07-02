package test;

import com.gysoft.jdbc.bean.*;
import com.gysoft.jdbc.test.bean.SimpleUser;
import com.gysoft.jdbc.test.bean.UserRole;
import com.gysoft.jdbc.test.dao.TbAccountDao;
import com.gysoft.jdbc.test.dao.TbUserDao;
import com.gysoft.jdbc.test.pojo.TbAccount;
import com.gysoft.jdbc.test.pojo.TbRole;
import com.gysoft.jdbc.test.pojo.TbUser;
import com.gysoft.jdbc.tools.CustomResultSetExractorFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.gysoft.jdbc.bean.FuncBuilder.*;

/**
 * @author 周宁
 * @Date 2018-09-21 15:49
 */

public class TestGyJdbc {

    @Test
    public void testInsert() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbUserDao tbUserDao = (TbUserDao) ac.getBean("tbUserDao");
        List<TbUser> tbUsers = new ArrayList<>();

        TbUser tbUser1 = new TbUser();
        tbUser1.setAge(26);
        tbUser1.setBirth(LocalDateToDate(LocalDate.of(1993, 8, 27)));
        tbUser1.setCareer("Java");
        tbUser1.setEmail("22888@qq.com");
        tbUser1.setMobile("17788888888");
        tbUser1.setName("zhouning");
        tbUser1.setRealName("周宁");
        tbUser1.setPwd("123456");
        tbUser1.setIsActive(0);
        tbUser1.setRoleId(1);
        tbUser1.setId(genId());

        TbUser tbUser2 = new TbUser();
        tbUser2.setAge(27);
        tbUser2.setBirth(LocalDateToDate(LocalDate.of(1992, 9, 23)));
        tbUser2.setCareer("Java");
        tbUser2.setEmail("3334444@qq.com");
        tbUser2.setMobile("18888888888");
        tbUser2.setName("chengyl");
        tbUser2.setRealName("程元麟");
        tbUser2.setPwd("123456");
        tbUser2.setIsActive(0);
        tbUser2.setRoleId(3);
        tbUser2.setId(genId());

        TbUser tbUser3 = new TbUser();
        tbUser3.setAge(30);
        tbUser3.setBirth(LocalDateToDate(LocalDate.of(1989, 1, 22)));
        tbUser3.setCareer("C++");
        tbUser3.setEmail("5556666@qq.com");
        tbUser3.setMobile("19988888888");
        tbUser3.setName("yinhw");
        tbUser3.setRealName("殷宏伟");
        tbUser3.setPwd("123456");
        tbUser3.setIsActive(1);
        tbUser3.setRoleId(2);
        tbUser3.setId(genId());

        TbUser tbUser4 = new TbUser();
        tbUser4.setAge(30);
        tbUser4.setBirth(LocalDateToDate(LocalDate.of(1989, 1, 22)));
        tbUser4.setCareer("IOS");
        tbUser4.setEmail("7777888@qq.com");
        tbUser4.setMobile("13888888888");
        tbUser4.setName("Smith");
        tbUser4.setRealName("LiSen");
        tbUser4.setPwd("123456");
        tbUser4.setIsActive(1);
        tbUser4.setRoleId(3);
        tbUser4.setId(genId());

        TbUser tbUser5 = new TbUser();
        tbUser5.setAge(24);
        tbUser5.setBirth(LocalDateToDate(LocalDate.of(1995, 4, 12)));
        tbUser5.setCareer("JAVA");
        tbUser5.setEmail("89800@qq.com");
        tbUser5.setMobile("18355555555");
        tbUser5.setName("hxf");
        tbUser5.setRealName("何小飞");
        tbUser5.setPwd("123456");
        tbUser5.setIsActive(1);
        tbUser5.setRoleId(3);
        tbUser5.setId(genId());

        tbUsers.add(tbUser1);
        tbUsers.add(tbUser2);
        tbUsers.add(tbUser3);
        tbUsers.add(tbUser4);
        tbUsers.add(tbUser5);
        tbUserDao.batchSave(tbUsers);
    }

    @Test
    public void testQuery() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbUserDao tbUserDao = (TbUserDao) ac.getBean("tbUserDao");
        List<TbUser> result1 = tbUserDao.queryAll();
        TbUser result2 = tbUserDao.queryOne(result1.get(0).getId());
        System.out.println("queryAll():" + result1);
        System.out.println("queryOne:" + result2);
    }

    @Test
    public void testQueryWithCriteria() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbUserDao tbUserDao = (TbUserDao) ac.getBean("tbUserDao");
        //根据用户名查询:SELECT * FROM tb_user where name = 'zhouning'
        TbUser tbUser = tbUserDao.queryOne(new Criteria().where(TbUser::getName, "zhouning").andIfAbsent(TbUser::getName, null));
        System.out.println("queryOne:" + tbUser);
        //根据用户名批量查询:SELECT * FROM tb_user where name in('zhouning','yinhw');
        List<TbUser> tbUsers = tbUserDao.queryWithCriteria(new Criteria().in(TbUser::getName, Arrays.asList("zhouning", "yinhw")));
        System.out.println("queryWithCriteria:" + tbUsers);
        //根据关键字和年龄模糊查询:SELECT * FROM tb_user where age > 26 and (realName like '%l%' or name like '%l%')
        String searchKey = "l";
        List<TbUser> tbUsers2 = tbUserDao.queryWithCriteria(new Criteria().gt(TbUser::getAge, 26).andCriteria(new Criteria().like(TbUser::getRealName, searchKey).or(TbUser::getName, "like", "%" + searchKey + "%")));
        System.out.println("queryWithCriteria:" + tbUsers2);
        //根据关键字搜索，关键字空或者null则不传:SELECT * FROM tb_user
        searchKey = "";
        List<TbUser> tbUsers3 = tbUserDao.queryWithCriteria(new Criteria().likeIfAbsent(TbUser::getName, searchKey));
        System.out.println("queryWithCriteria:" + tbUsers3);
        //分页查询:SELECT * FROM tb_user LIMIT 0,2
        PageResult<TbUser> pageResult = tbUserDao.pageQuery(new Page(1, 2));
        System.out.println("pageQuery:" + pageResult);
        //分页条件查询:SELECT * FROM tb_user WHERE age < 28 LIMIT 0,2
        PageResult<TbUser> pageResult2 = tbUserDao.pageQueryWithCriteria(new Page(1, 2), new Criteria().lt(TbUser::getAge, 28));
        System.out.println("pageQueryWithCriteria:" + pageResult2);
        //按年龄降序查询用户:SELECT * FROM tb_user ORDER BY age DESC
        List<TbUser> tbUsers4 = tbUserDao.queryWithCriteria(new Criteria().orderBy(new Sort(TbUser::getAge)));
        System.out.println("queryWithCriteria:" + tbUsers4);
    }

    @Test
    public void testUpdate() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbUserDao tbUserDao = (TbUserDao) ac.getBean("tbUserDao");
        //更新一个用户
        TbUser tbUser = tbUserDao.queryOne(new Criteria().and(TbUser::getName, "zhouning"));
        tbUser.setRealName("周宁宁");
        tbUser.setEmail("2267431887@qq.com");
        tbUserDao.update(tbUser);
        //更新全部用户
        List<TbUser> tbUsers = tbUserDao.queryAll();
        for (TbUser tUser : tbUsers) {
            tUser.setIsActive(1);
        }
        tbUserDao.batchUpdate(tbUsers);
        //根据SQL更新某个用户:UPDATE tb_user SET realName = '李森',email='1388888888@163.com' where name = 'Smith'
        tbUserDao.updateWithSql(new SQL().update(TbUser::getRealName, "李森").update(TbUser::getEmail, "13888888888@163.com").where(TbUser::getName, "Smith"));
    }

    @Test
    public void testUseSQL() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbUserDao tbUserDao = (TbUserDao) ac.getBean("tbUserDao");
        //SELECT name, email, realName, mobile FROM tb_user WHERE isActive = 1
        SQL sql = new SQL().select(TbUser::getName, TbUser::getEmail, TbUser::getRealName, TbUser::getMobile)
                .from(TbUser.class).where(TbUser::getIsActive, 1);
        List<SimpleUser> simpleUsers = tbUserDao.queryWithSql(SimpleUser.class, sql).queryList();
        System.out.println("queryWithSql:" + simpleUsers);
        //SELECT name, email, realName, mobile FROM tb_user WHERE isActive = 1 limit 0,2
        SQL sql2 = new SQL().select(TbUser::getName, TbUser::getEmail, TbUser::getRealName, TbUser::getMobile)
                .from(TbUser.class).where(TbUser::getIsActive, 1);
        PageResult<SimpleUser> simpleUsers2 = tbUserDao.queryWithSql(SimpleUser.class, sql2).pageQuery(new Page(1, 2));
        System.out.println("queryWithSql:" + simpleUsers2);
        //SELECT count(id) from tb_user
        SQL sql3 = new SQL().select(count(TbUser::getId)).from(TbUser.class);
        Integer count = tbUserDao.queryIntegerWithSql(sql3);
        Integer count2 = tbUserDao.queryWithSql(Integer.class, sql3).queryObject();
        System.out.println("queryIntegerWithSql:" + count);
        System.out.println("queryWithSql:" + count2);
        //SELECT age, COUNT(age) AS num FROM tb_user GROUP BY age ORDER BY age DESC
        SQL sql4 = new SQL().select("age", countAs("age").as("num")).from(TbUser.class).orderBy(new Sort(TbUser::getAge)).groupBy(TbUser::getAge);
        Map<Integer, Integer> map = tbUserDao.queryMapWithSql(sql4, CustomResultSetExractorFactory.createDoubleColumnValueResultSetExractor());
        System.out.println("queryMapWithSql:" + map);
        //SELECT DISTINCT(career) FROM tb_user
        SQL sql5 = new SQL().select(distinct(TbUser::getCareer)).from(TbUser.class);
        List<String> careers = tbUserDao.queryWithSql(String.class, sql5).queryForList();
        System.out.println("queryWithSql" + careers);
        //SELECT t1.name,t1.realName,t2.id,t2.roleName FROM tb_user AS t1 LEFT JOIN tb_role AS t2  ON t1.roleId = t2.id  WHERE t1.age > ?
        SQL sql6 = new SQL().select("t1.name,t1.realName,t2.id,t2.roleName").from(TbUser.class)
                .as("t1").leftJoin(new Joins().with(TbRole.class).as("t2").on("t1.roleId", "t2.id"))
                .where("t1.age", ">", 24);
        List<UserRole> userRoles = tbUserDao.queryWithSql(UserRole.class, sql6).queryList();
        System.out.println("queryWithSql:" + userRoles);
        //以下SQL仅仅用来演示SQL功能，工作中切勿这么些
        //SELECT roleId FROM( SELECT DISTINCT(t.roleId) AS roleId FROM tb_user AS t UNION ALL SELECT DISTINCT(t1.roleId) AS roleId FROM tb_user AS t1)  AS t2
        SQL sql7 = new SQL().select("roleId").from(new SQL().select(distinctAs("t.roleId").as("roleId")).from(TbUser.class).as("t")
                , new SQL().select(distinctAs("t1.roleId").as("roleId")).from(TbUser.class).as("t1")).as("t2");
        List<Integer> inUseRoleId = tbUserDao.queryWithSql(Integer.class, sql7).queryForList();
        System.out.println("queryWithSql:" + inUseRoleId);
        //(SELECT t1.name,t1.realName,t2.id,t2.roleName FROM tb_user AS t1 LEFT JOIN tb_role AS t2  ON t1.roleId = t2.id  WHERE t1.age > 24)
        // UNION
        // (SELECT t3.name,t3.realName,t4.id,t4.roleName FROM tb_user AS t3 LEFT JOIN tb_role AS t4  ON t3.roleId = t4.id  WHERE t3.career IN('JAVA'))
        SQL sql8 = new SQL().select("t1.name,t1.realName,t2.id,t2.roleName").from(TbUser.class)
                .as("t1").leftJoin(new Joins().with(TbRole.class).as("t2").on("t1.roleId", "t2.id"))
                .where("t1.age", ">", 24).union().select("t3.name,t3.realName,t4.id,t4.roleName").from(TbUser.class)
                .as("t3").leftJoin(new Joins().with(TbRole.class).as("t4").on("t3.roleId", "t4.id"))
                .in("t3.career", Arrays.asList("JAVA"));
        List<UserRole> userRoles2 = tbUserDao.queryWithSql(UserRole.class, sql8).queryList();
        System.out.println(userRoles2);
        //more ...............................
    }

    @Test
    public void testInsertWithSql() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbUserDao tbUserDao = (TbUserDao) ac.getBean("tbUserDao");
        TbAccountDao tbAccountDao = (TbAccountDao) ac.getBean("tbAccountDao");
        SQL sql = new SQL().insert("id", "name", "realName", "pwd", "email", "mobile", "age", "birth", "roleId", "career", "isActive")
                .values(4, "ins4", "插入1", "123456", "345@qq.com", "12345678901", 25, new Date(), 1, "测试", 1)
                .values(5, "ins5", "插入2", "123456", "456@qq.com", "12345678901", 26, new Date(), 1, "测试", 1)
                .values(6, "ins6", "插入3", "123456", "567@qq.com", "12345678901", 27, new Date(), 1, "测试", 0);
        SQL sql2 = new SQL().insert("userName", "realName")
                .select("name", "realName").from(TbUser.class);

        SQL sql3 = new SQL().insert(TbAccount::getUserName, TbAccount::getRealName)
                .select("name", "realName").from(TbUser.class).gt(TbUser::getIsActive, 0);

        tbUserDao.insertWithSql(sql);
        tbAccountDao.insertWithSql(sql2);
        tbAccountDao.insertWithSql(sql3);
    }

    @Test
    public void testDelete() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbUserDao tbUserDao = (TbUserDao) ac.getBean("tbUserDao");
        tbUserDao.delete("0");
        tbUserDao.batchDelete(tbUserDao.queryAll().stream().map(TbUser::getId).collect(Collectors.toList()));
        tbUserDao.deleteWithCriteria(new Criteria().where(TbUser::getIsActive,0));
    }

    private static Date LocalDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ChronoZonedDateTime<LocalDate> zonedDateTime = localDate.atStartOfDay(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    private static String genId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Test
    public void testCreate() throws Exception {
        SQL sql = new SQL().createTable()
                .addColumn().name("id").integer().notNull().autoIncrement().primary().comment("主键").commit()
                .addColumn().name("userName").varchar(50).notNull().comment("账号").commit()
                .addColumn().name("realName").varchar(50).defaultNull().comment("真实名称").commit()
                .engine(TableEngine.MyISAM).comment("账号表2").commit()
//                .values(0,"zhouning","周宁");
//                .values(0,"pengjiajia","彭佳佳");
                .select("0,userName,realName").from(TbAccount.class);//支持select语句的插入方法
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbAccountDao tbAccountDao = (TbAccountDao) ac.getBean("tbAccountDao");
        String tbName = tbAccountDao.createWithSql(sql);
        //通过临时表进行inner join left join 等骚操作
        SQL qSql = new SQL().select("*").from(tbName);
        System.out.println(tbAccountDao.queryWithSql(TbAccount.class, qSql).queryList());
    }

    @Test
    public void testUseTmpTableQuery() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        TbAccountDao tbAccountDao = (TbAccountDao) ac.getBean("tbAccountDao");
        SQL sql = new SQL().select("*").from(TbAccount.class).as("a")
                .innerJoin(new Joins().with(tbAccountDao.createWithSql(
                        new SQL().createTable()
                                .addColumn().name("id").integer().primary().notNull().autoIncrement().commit()
                                .addColumn().name("userName").varchar(50).notNull().commit()
                                .index().name("ix_userName").column("userName").commit()
                                .engine(TableEngine.MyISAM).comment("用户临时表").commit()
                                .select("0", "name").from(TbUser.class)
//                                .values("0","zhouning")
                )).as("b").on("a.userName", "b.userName"));
        List<TbAccount> result = tbAccountDao.queryWithSql(TbAccount.class,sql).queryList();
        System.out.println(result);
        System.out.println(result.size());
    }

}
