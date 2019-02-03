package com.liwy.study.spring.spring4.bean;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 19:32 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class IocBean {
    private int id;
    private String name;
    private InstansBean instansBean;
    public IocBean(int id, String name) {
        System.out.println("通过构造方法注入");
        this.id = id;
        this.name = name;
    }

    public IocBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        System.out.println("通过set方法注入id");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("通过set方法注入name");
        this.name = name;
    }

    public InstansBean getInstansBean() {
        return instansBean;
    }

    public void setInstansBean(InstansBean instansBean) {
        System.out.println("通过set方法注入instansBean");
        this.instansBean = instansBean;
    }

    @Override
    public String toString() {
        return "IocBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instansBean=" + instansBean +
                '}';
    }
}
