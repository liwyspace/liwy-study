package com.liwy.study.orm.hibernate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * <b>名称：</b> 栏目实体类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/28 14:33 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Entity
@Table(name = "liwy_channel")
public class Channel implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id; // 栏目ID

    @Column(name = "parent_id")
    private Integer parentId; // 上级栏目ID

    @Column(name = "priority")
    private Integer priority; // 排列顺序

    private String name; // 栏目名称

    private Byte isBlank; // 是否打开新窗口

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Content> contentList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getIsBlank() {
        return isBlank;
    }

    public void setIsBlank(Byte isBlank) {
        this.isBlank = isBlank;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", priority=" + priority +
                ", name='" + name + '\'' +
                ", isBlank=" + isBlank +
                '}';
    }
}
