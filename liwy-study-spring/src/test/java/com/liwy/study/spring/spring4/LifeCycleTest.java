package com.liwy.study.spring.spring4;

import com.liwy.study.spring.spring4.bean.InstansBean;
import com.liwy.study.spring.spring4.bean.LifeCycleBean;
import com.liwy.study.spring.spring4.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <b>名称：</b> Bean的生命周期回调函数测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:46 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class LifeCycleTest {

    /**
     * <b>描述：</b> 测试回调函数,可关闭的容器<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testLifeCycle() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-lifeCycle.xml"); // 可同时加载多个配置文件
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-lifeCycle.xml"); // 可关闭的容器
        context.close();
    }
}
