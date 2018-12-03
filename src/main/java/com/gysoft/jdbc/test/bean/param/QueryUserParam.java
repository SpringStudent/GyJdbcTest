package com.gysoft.jdbc.test.bean.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 周宁
 * @Date 2018-12-03 14:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryUserParam {

    private String searchKey;

    private Date birth;
}
