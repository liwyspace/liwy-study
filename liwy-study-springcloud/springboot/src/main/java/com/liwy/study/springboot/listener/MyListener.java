package com.liwy.study.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/3/1 18:42 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context启动啦");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context销毁了");
    }
}
