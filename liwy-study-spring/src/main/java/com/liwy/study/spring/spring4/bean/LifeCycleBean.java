package com.liwy.study.spring.spring4.bean;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/3 14:10 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class LifeCycleBean {
    private void initDef() {
        System.out.println("beans上设置的初始化回调函数");
    }
    private void destroyDef() {
        System.out.println("beans上设置的析构回调函数");
    }

    private void myinit() {
        System.out.println("bean特有的初始化回调函数");
    }

    private void mydestroy() {
        System.out.println("bean特有的析构回调函数");
    }
}
