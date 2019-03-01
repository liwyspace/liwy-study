package com.liwy.study.springboot.dao;

import com.liwy.study.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:41 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Repository("iUserDao")
public interface IUserDao {
    Long getUserCount();

    List<User> queryUserList();

    User getUser(Long id);

    String getUserName(Long id);

    void updateUser(User user);

    void delete(Long id);

    int insertUser(User user);

    void insertUserGetId(User user);

    void batchUserList(List<User> users);

    void batchInsert(List<User> users);

    void getEntityCount(Integer chId);

    void simpleInsertUser(User user);

    void simpleGetEntityCount(Integer chId);

    void simpleQueryChannelTag();

    List<User> selectUser(User param);
}
