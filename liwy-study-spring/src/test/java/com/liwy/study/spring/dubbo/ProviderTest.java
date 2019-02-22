package com.liwy.study.spring.dubbo;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/22 14:44 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class ProviderTest {
    @Test
    public void runProvider() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/applicationContext-dubbo-provider.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }
}
