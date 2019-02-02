package com.liwy.study.orm.mybatis.bo;

import com.liwy.study.orm.mybatis.entity.Channel;
import com.liwy.study.orm.mybatis.entity.Content;
import com.liwy.study.orm.mybatis.entity.Tag;
import com.liwy.study.orm.mybatis.entity.User;

import java.util.Date;
import java.util.List;

/**
 * <b>名称：</b> 内容实体扩展类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/28 15:12 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class ContentBo extends Content {
    private Channel channel; // 栏目
    private User author; // 作者
    private List<Tag> tagList; // 标签列表

    private Date createTimeStart;
    private Date createTimeEnd;
    private Long contentNum; // 文章数量
    private Long tagNum; // 标签数量
    private String copyRight; // 版权，测试ObjectFactory
    private List<Long> idList;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public Long getContentNum() {
        return contentNum;
    }

    public void setContentNum(Long contentNum) {
        this.contentNum = contentNum;
    }

    public Long getTagNum() {
        return tagNum;
    }

    public void setTagNum(Long tagNum) {
        this.tagNum = tagNum;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    @Override
    public String toString() {
        return "ContentBo{" +
                super.toString() +
                "channel=" + channel +
                ", author=" + author +
                ", tagList=" + tagList +
                ", contentNum=" + contentNum +
                ", tagNum=" + tagNum +
                ", copyRight=" + copyRight +
                '}';
    }
}
