package com.liwy.study.mybatis.entity;

import java.io.Serializable;

/**
 * <b>名称：</b> 标签实体类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/28 15:05 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class Tag implements Serializable {
    private Long id; // 标签主键
    private String name; // 标签名称
    private Byte status; // 标签状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
