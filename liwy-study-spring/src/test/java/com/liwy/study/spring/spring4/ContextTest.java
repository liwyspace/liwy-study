package com.liwy.study.spring.spring4;

import com.liwy.study.spring.spring4.bean.InstansBean;
import com.liwy.study.spring.spring4.dao.IUserDao;
import com.liwy.study.spring.spring4.service.IUserService;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.logging.Logger;

/**
 * <b>名称：</b> 基础测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:46 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class ContextTest {
    /**
     * <b>描述：</b> 测试使用spring容器,和默认日志<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testApplicationContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-context.xml"); // 可同时加载多个配置文件
        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println(userService.sayHello());

        InstansBean instansBean = context.getBean("instansBean", InstansBean.class);
        System.out.println(instansBean);
    }
}
