package com.liwy.study.spring.spring4.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/3 14:17 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class LifeCycleBeanInt implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("LifeCycleBeanInt析构回调函数");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("LifeCycleBeanInt初始化回调函数");
    }
}
