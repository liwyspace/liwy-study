package com.liwy.study.mybatis.entity;

import com.liwy.study.mybatis.bo.SexEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>名称：</b> 用户实体类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/28 14:50 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class User implements Serializable {
    private Long id; // 用户ID
    private String username; // 用户名
    private SexEnum sex; // 性别
    private String email; // 注册邮箱
    private Date registerTime; // 注册时间
    private Byte status; // 状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", registerTime=" + registerTime +
                ", status=" + status +
                '}';
    }
}
