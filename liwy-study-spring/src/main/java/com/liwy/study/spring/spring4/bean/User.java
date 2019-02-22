package com.liwy.study.spring.spring4.bean;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.javafx.beans.IDProperty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/14 17:28 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Entity
@Table(name = "liwy_user_2")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, name = "username")
    private String username;
    private Byte sex;
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerTime;
    private Byte status;
    @Lob
    private String content;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] icon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(WithNameEmailView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    @JsonView(WithNameEmailView.class)
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
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
                ", content='" + content + '\'' +
                ", icon=" + Arrays.toString(icon) +
                '}';
    }


    // springmvc 视图
    public interface WithNameEmailView {};

}
