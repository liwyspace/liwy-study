package com.liwy.study.spring.spring4;

import com.liwy.study.spring.spring4.bean.AnnotationBean;
import com.liwy.study.spring.spring4.bean.InstansBean;
import com.liwy.study.spring.spring4.bean.IocBean;
import com.liwy.study.spring.spring4.bean.JavaContext;
import com.liwy.study.spring.spring4.bean.PropertyBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <b>名称：</b> 基于Java的容器配置测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/3 15:16 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class JavaContextTest {

    /**
     * <b>描述：</b> 测试基于java的无xml配置<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testContex() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaContext.class);
        PropertyBean propertyBean = context.getBean("propertyBean", PropertyBean.class);
        System.out.println(propertyBean);
        InstansBean instansBean = context.getBean("instansBean", InstansBean.class);
        System.out.println(instansBean);
        IocBean iocBean = context.getBean("iocBean", IocBean.class);
        System.out.println(iocBean);
        AnnotationBean annotationBean = context.getBean("annotationBean", AnnotationBean.class);
        System.out.println(annotationBean);
    }
}
