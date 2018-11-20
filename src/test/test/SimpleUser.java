package test;

import lombok.Data;

import java.util.Date;

/**
 * @author 周宁
 * @Date 2018-09-21 17:15
 */
@Data
public class SimpleUser {

    private String name;

    private String email;

    private Date birth;
}
