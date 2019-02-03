package com.liwy.study.spring.spring4.bean;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * <b>名称：</b> spring事件监听类<br/>
 * <b>描述：</b>
 * ContextRefreshedEvent：在ApplicationContext初始化或刷新时发布
 * ContextStartedEvent：在ApplicationContext启动时发布，调用ConfigurableApplicationContext的start()方法时发布
 * ContextStoppedEvent：ApplicationContext停止发布时，调用ConfigurableApplicationContext的stop()方法时发布
 * ContextClosedEvent：在ApplicationContext关闭时发布
 * RequestHandledEvent：一个Web特定的事件，告诉所有的bean一个HTTP请求已经被服务。
 *
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/3 15:54 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class SpringEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("==============================");
        System.out.println("ApplicationContext初始化");
        System.out.println("==============================");
    }
}
