package com.liwy.study.spring.spring4;

import com.liwy.study.spring.spring4.bean.InstansBean;
import com.liwy.study.spring.spring4.bean.IocBean;
import com.liwy.study.spring.spring4.bean.PropertyBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> IOC注入测试<br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 19:29 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class IocTest {
    /**
     * <b>描述：</b> 测试依赖注入<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testIoc() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring4/applicationContext-ioc.xml");
        IocBean iocBean = applicationContext.getBean("iocBean", IocBean.class);
        System.out.println(iocBean);

        IocBean iocBean2 = applicationContext.getBean("iocBean2", IocBean.class);
        System.out.println(iocBean2);
    }

    /**
     * <b>描述：</b> bean参数详解测试<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testPropertyBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring4/applicationContext-ioc.xml");
        PropertyBean propertyBean = applicationContext.getBean("propertyBean", PropertyBean.class);
        System.out.println(propertyBean);
        PropertyBean propertyBean2 = applicationContext.getBean("propertyBean2", PropertyBean.class);
        System.out.println(propertyBean2);
        PropertyBean propertyBean3 = applicationContext.getBean("propertyBean3", PropertyBean.class);
        System.out.println(propertyBean3);
        PropertyBean propertyBean4 = applicationContext.getBean("propertyBean4", PropertyBean.class);
        System.out.println(propertyBean4);
        PropertyBean propertyBean5 = applicationContext.getBean("propertyBean5", PropertyBean.class);
        System.out.println(propertyBean5);
        PropertyBean propertyBean6 = applicationContext.getBean("propertyBean6", PropertyBean.class);
        System.out.println(propertyBean6);
        PropertyBean propertyBean7 = applicationContext.getBean("propertyBean7", PropertyBean.class);
        System.out.println(propertyBean7);
        PropertyBean propertyBean8 = applicationContext.getBean("propertyBean8", PropertyBean.class);
        System.out.println(propertyBean8);
        PropertyBean child = applicationContext.getBean("child", PropertyBean.class);
        System.out.println(child);
        PropertyBean propertyBean9 = applicationContext.getBean("propertyBean9", PropertyBean.class);
        System.out.println(propertyBean9.getNameList().size());
        System.out.println(propertyBean9);
        InstansBean instansBean9 = applicationContext.getBean("instansBean9", InstansBean.class);
        System.out.println(instansBean9);
    }
}
