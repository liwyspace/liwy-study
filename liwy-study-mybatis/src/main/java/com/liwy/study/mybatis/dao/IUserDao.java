package com.liwy.study.mybatis.dao;

import com.liwy.study.mybatis.bo.ContentBo;
import com.liwy.study.mybatis.entity.Content;
import com.liwy.study.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <b>名称：</b> 用户数据访问接口<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/28 15:52 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public interface IUserDao {
    /**
     * <b>描述：</b> 插入用户<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param user
     * @return int
     */
    int insertUser(User user);

    /**
     * <b>描述：</b> 查询用户列表<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return java.util.List<com.liwy.study.mybatis.entity.User>
     */
    List<User> selectUser(User param);

    /**
     * <b>描述：</b> 获取用户信息<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param id
     * @return com.liwy.study.mybatis.entity.User
     */
    User getUser(Long id);

}
