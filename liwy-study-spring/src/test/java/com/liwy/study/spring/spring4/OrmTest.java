package com.liwy.study.spring.spring4;

import com.liwy.study.spring.spring4.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/18 16:01 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class OrmTest {
    @Test
    public void testMybatis() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-mybatis.xml");
        IUserService userService = context.getBean(IUserService.class);
        userService.insertTowUser();
    }

    @Test
    public void testHibernate() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-hibernate.xml");
        IUserService userService = context.getBean(IUserService.class);
        userService.insertTowUser();
    }

    @Test
    public void testJpa() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-jpa.xml");
        IUserService userService = context.getBean(IUserService.class);
        userService.insertTowUser();
    }
}
