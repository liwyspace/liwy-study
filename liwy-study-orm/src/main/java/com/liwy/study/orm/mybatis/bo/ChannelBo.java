package com.liwy.study.orm.mybatis.bo;

import com.liwy.study.orm.mybatis.entity.Channel;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/30 14:43 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class ChannelBo extends Channel {
    private Channel parent; // 上级栏目

    public Channel getParent() {
        return parent;
    }

    public void setParent(Channel parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "ChannelBo{" +
                super.toString() + "," +
                "parent=" + parent +
                '}';
    }
}
