package com.liwy.study.orm.mybatis;

import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>名称：</b> SQL构造工具测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/29 10:56 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class SqlBuilderTest {
    private final Logger logger = LoggerFactory.getLogger(SqlBuilderTest.class);

    @Test
    public void testSql() {
        String sql = new SQL().SELECT("id", "name", "password").FROM("liwy_test")
                .WHERE("id=#{id}").AND().WHERE("name like #{name}").toString();
        logger.info(sql);

        String sql1 = new SQL() {{
            SELECT("id", "name");
            FROM("liwy_test");
            WHERE("id=#{id}");
            WHERE("name=#{name}");
        }}.toString();
        logger.info(sql1);
    }
}
