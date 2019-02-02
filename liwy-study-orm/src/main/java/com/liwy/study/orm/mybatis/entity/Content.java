package com.liwy.study.orm.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>名称：</b> 内容实体类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/28 14:49 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class Content implements Serializable {
    private Long id; // 内容ID
    private Integer channelId; // 栏目ID
    private Long userId; // 用户ID
    private String tex; // 主体内容
    private String createBy; // 活动创建人OA帐号
    private Date createTime; // 创建时间
    private String updateBy; // 最后修改人
    private Date updateTime; // 更新时间
    private Byte isDeleted; // 是否已经删除，0：未删除，1：删除

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTex() {
        return tex;
    }

    public void setTex(String tex) {
        this.tex = tex;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", channelId=" + channelId +
                ", userId=" + userId +
                ", tex='" + tex + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
