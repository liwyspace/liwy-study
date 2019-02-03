package com.liwy.study.spring.spring4.bean;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 19:00 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class InstansBean {
    private String name;
    public InstansBean(String name) {
        System.out.println("通过构造方法创建");
        this.name = name;
    }

    public static InstansBean createInstansBean(String name) {
        System.out.println("通过静态工厂方法创建实例");
        return new InstansBean(name);
    }

    @Override
    public String toString() {
        return "InstansBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
