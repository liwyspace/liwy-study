package com.liwy.study.orm.mybatis.objectFactory;

import com.liwy.study.orm.mybatis.bo.ContentBo;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

/**
 * <b>名称：</b> 自定义对象工厂<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/27 17:34 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class MyObjectFactory extends DefaultObjectFactory {
    @Override
    public <T> T create(Class<T> type) {
        T result = super.create(type);
        if (type.equals(ContentBo.class)) {
            ((ContentBo) result).setCopyRight("www.oscafe.net");
        }
        return result;
    }
}
