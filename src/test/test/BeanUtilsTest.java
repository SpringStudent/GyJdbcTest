package test;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 周宁
 * @Date 2018-08-06 10:08
 */
public class BeanUtilsTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //属性赋值
        Class clzz = Class.forName("test.Student");
        Object obj = clzz.newInstance();
        ConvertUtils.register(new DateLocaleConverter(), Date.class);
        BeanUtils.copyProperty(obj, "id", "1");
        BeanUtils.copyProperty(obj, "name", "VN");
        BeanUtils.copyProperty(obj, "age", 22);
        BeanUtils.copyProperty(obj, "classId", "22");
        BeanUtils.copyProperty(obj, "birthday", "1993-08-27");
        System.out.println("copyProperty test:" + obj);

        //对象赋值
        Student s1 = new Student();
        Student s2 = new Student();
        s1.setName("zhouning");
        s1.setId("1");
        s1.setAge(23);
        s1.setBirthday(new Date());
        BeanUtils.copyProperties(s2, s1);
        System.out.println("copyProperties test:" + s2);

        //把一个map集合中的数据拷贝到javaBean中
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("name", "eason");
        map.put("age", 45);
        map.put("classId", 4);
        map.put("birthday", new Date());
        Student s3 = new Student();
        BeanUtils.copyProperties(s3, map);
        System.out.println("map to bean:" + s3);

    }

}
