package com.liwy.study.spring.spring4;

import com.liwy.study.spring.spring4.bean.LifeCycleBean;
import com.liwy.study.spring.spring4.bean.PropertyBean;
import com.liwy.study.spring.spring4.bean.SpringContextUtil;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <b>名称：</b> 测试ApplicationContextAware接口获得容器对象<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:46 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class SpringContextAwareTest {

    /**
     * <b>描述：</b> 测试通过实现ApplicationContextAware接口的spring工具类<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testAware() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-aware.xml");
        PropertyBean bean = SpringContextUtil.getBean("propertyBean", PropertyBean.class);
        System.out.println(bean);
        context.close();
    }
}
