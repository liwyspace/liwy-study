package com.liwy.study.spring.spring4.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 19:00 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class PropertyBean {
    private Integer id;
    private String name;
    private IocBean iocBean;
    private Properties properties;
    private List<String> nameList;
    private Set<String> nameSet;
    private Map<Integer, String> nameMap;
    private IocBean iocBean2 = new IocBean();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IocBean getIocBean() {
        return iocBean;
    }

    public void setIocBean(IocBean iocBean) {
        this.iocBean = iocBean;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public Set<String> getNameSet() {
        return nameSet;
    }

    public void setNameSet(Set<String> nameSet) {
        this.nameSet = nameSet;
    }

    public Map<Integer, String> getNameMap() {
        return nameMap;
    }

    public void setNameMap(Map<Integer, String> nameMap) {
        this.nameMap = nameMap;
    }

    public IocBean getIocBean2() {
        return iocBean2;
    }

    public void setIocBean2(IocBean iocBean2) {
        this.iocBean2 = iocBean2;
    }

    @Override
    public String toString() {
        return "PropertyBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", iocBean=" + iocBean +
                ", properties=" + properties +
                ", nameList=" + nameList +
                ", nameSet=" + nameSet +
                ", nameMap=" + nameMap +
                ", iocBean2=" + iocBean2 +
                '}';
    }
}
