package com.liwy.study.spring.spring4;

import com.liwy.study.spring.spring4.bean.InstansBean;
import com.liwy.study.spring.spring4.service.IUserService;
import com.liwy.study.spring.spring4.service.impl.UserServiceImpl;
import org.hibernate.annotations.SourceType;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * <b>名称：</b> Bean测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:46 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class BeanTest {
    /**
     * <b>描述：</b> 测试别名<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testAlias() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-bean.xml"); // 可同时加载多个配置文件
        IUserService userService = context.getBean("userServiceAlias", IUserService.class);
        System.out.println(userService);
    }

    /**
     * <b>描述：</b> 测试实例化bean<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testInstans() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-bean.xml"); // 可同时加载多个配置文件
        InstansBean instansBean = context.getBean("instansBean", InstansBean.class);
        System.out.println(instansBean);
        InstansBean instansBean2 = context.getBean("instansBean2", InstansBean.class);
        System.out.println(instansBean2);
        InstansBean instansBean3 = context.getBean("instansBean3", InstansBean.class);
        System.out.println(instansBean3);
    }
}
