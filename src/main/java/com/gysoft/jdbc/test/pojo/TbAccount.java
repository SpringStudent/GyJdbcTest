package com.gysoft.jdbc.test.pojo;

import com.gysoft.jdbc.annotation.Table;
import lombok.Data;

/**
 * @author 周宁
 * @Date 2019-05-15 15:49
 */
@Data
@Table(name = "tb_account")
public class TbAccount {

    private Integer id;

    private String userName;

    private String realName;
}
