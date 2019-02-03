package com.liwy.study.spring.spring4.bean;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 19:16 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class InstansBeanFactory {
    public InstansBean createInstansBean(String name) {
        System.out.println("通过实例工厂创建bean");
        return new InstansBean(name);
    }
}
