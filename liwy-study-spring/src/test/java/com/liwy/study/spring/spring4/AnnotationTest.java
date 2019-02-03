package com.liwy.study.spring.spring4;

import com.liwy.study.spring.spring4.bean.AnnotationBean;
import com.liwy.study.spring.spring4.bean.PropertyBean;
import com.liwy.study.spring.spring4.bean.SpringContextUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <b>名称：</b> 注解方式测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:46 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class AnnotationTest {

    /**
     * <b>描述：</b> 测试注解方式<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testAnnotation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-annotation.xml");
        AnnotationBean bean = context.getBean("annotationBean", AnnotationBean.class);
        System.out.println(bean);
    }
}
