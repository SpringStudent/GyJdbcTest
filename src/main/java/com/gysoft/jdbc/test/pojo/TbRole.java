package com.gysoft.jdbc.test.pojo;

import com.gysoft.jdbc.annotation.Table;
import lombok.Data;

/**
 * @author 周宁
 * @Date 2019-04-26 16:02
 */
@Data
@Table(name = "tb_role")
public class TbRole {

    private String id;

    private String roleName;
}
