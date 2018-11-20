package test;

import lombok.Data;

import java.util.Date;

/**
 * @author 周宁
 * @Date 2018-08-06 10:08
 */
@Data
public class Student {

    private String id;

    private String name;

    private int age;

    private int classId;

    private Date birthday;
}
