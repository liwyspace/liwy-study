package com.liwy.study.spring.spring4;

import com.liwy.study.spring.spring4.aop.AopService;
import com.liwy.study.spring.spring4.aop.AopServiceProxy;
import com.liwy.study.spring.spring4.service.IUserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <b>名称：</b> AOP测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/14 10:35 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class AopTest {
    /**
     * <b>描述：</b> 测试AspectJ方式的AOP<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testAspectJ() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-aspectJ.xml");
        AopService aopService = context.getBean("aopServiceOneImpl", AopService.class);
        aopService.sayHello("liwey");
        System.out.println("==================");
        aopService.printHello("liwyprint");
        System.out.println("==================");
        try {
            aopService.sayHelloThrow("liwythrow");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("==================");
        AopService aopService2 = context.getBean("aopServiceTwoImpl", AopService.class);
        aopService2.sayHello("nannan");
        System.out.println("==================");
        aopService2.printHello("nannaprint");
        System.out.println("==================");
        try {
            aopService2.sayHelloThrow("nannathrow");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("==================");
        AopServiceProxy aopServiceProxy = context.getBean("aopServiceProxy", AopServiceProxy.class);
        aopServiceProxy.sayHello();
    }

    /**
     * <b>描述：</b> 测试Spring AOP API<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testAopApi() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4/applicationContext-aop.xml");
        AopService aopService = context.getBean("myAopServiceProxy", AopService.class);
        aopService.printHello("liwey");
        System.out.println("===========");
        aopService.sayHello("nanana");
    }
}
