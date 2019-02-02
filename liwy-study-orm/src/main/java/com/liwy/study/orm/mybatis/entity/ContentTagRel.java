package com.liwy.study.orm.mybatis.entity;

/**
 * <b>名称：</b> 内容标签关系<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/28 15:08 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class ContentTagRel {
    private Long id; // 主键
    private Long contentId; // 内容ID
    private Long tagId; // 标签ID
    private Byte isDeleted; // 是否删除

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "ContentTagRel{" +
                "id=" + id +
                ", contentId=" + contentId +
                ", tagId=" + tagId +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
