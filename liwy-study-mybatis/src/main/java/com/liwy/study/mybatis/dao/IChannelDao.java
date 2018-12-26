package com.liwy.study.mybatis.dao;

import com.liwy.study.mybatis.bo.ChannelBo;

import java.util.Map;

/**
 * <b>名称：</b> 栏目数据访问接口<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/30 14:42 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public interface IChannelDao {
    /**
     * <b>描述：</b> 获取栏目详细信息<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param id
     * @return com.liwy.study.mybatis.bo.ChannelBo
     */
    ChannelBo getChannelBo(Integer id);
}
