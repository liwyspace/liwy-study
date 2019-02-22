package com.liwy.study.spring.spring4;

import com.liwy.study.spring.spring4.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/19 10:49 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/spring4/applicationContext-mybatis.xml")
@TestPropertySource(  // 读取属性文件
//        locations = "/test.properties",
        properties = {"timezone = GMT", "port: 4242"}
)
public class SpringTestTest{
    @Autowired
    private IUserService userService;

    @Value("${port}")
    private String port;

    @Transactional // 在事务中运行，结束后自动回滚
    @Sql("/spring4/insert.sql") // 执行前先执行sql
    @Test
    public void testIUserService() throws Exception {
        System.out.println(port);
        userService.insertTowUser();
    }
}
