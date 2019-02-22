package com.liwy.study.spring.dubbo;

import com.liwy.study.spring.spring4.service.IDubboService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/22 14:47 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class ConsumerTest {
    @Test
    public void testConsumer() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/applicationContext-dubbo-consumer.xml");
        context.start();
        IDubboService dubboService = (IDubboService) context
                .getBean("dubboService"); // 获取远程服务代理
        String hello = dubboService.sayHello("liwy"); // 执行远程方法
        System.out.println(hello); // 显示调用结果
    }
}
