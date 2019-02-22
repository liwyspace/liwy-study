package com.liwy.study.spring.spring4.dao.impl;

import com.liwy.study.spring.spring4.bean.Channel;
import com.liwy.study.spring.spring4.bean.Tag;
import com.liwy.study.spring.spring4.bean.User;
import com.liwy.study.spring.spring4.dao.IUserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:41 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Repository
public class UserDaoHibernateImpl implements IUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long getUserCount() {
        return null;
    }

    @Override
    public List<User> queryUserList() {
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User").list();
        return users;
    }

    @Override
    public User getUser(Long id) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
        return user;
    }

    @Override
    public String getUserName(Long id) {
        return "";
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public int insertUser(User user) {
        Long id = (Long) sessionFactory.getCurrentSession().save(user);
        System.out.println(id);
        return 0;
    }

    @Override
    public void insertUserGetId(User user) {
    }

    @Override
    public void batchUserList(List<User> users) {
    }

    @Override
    public void batchInsert(List<User> users) {
    }

    @Override
    public void getEntityCount(Integer chId) {
    }

    @Override
    public void simpleInsertUser(User user) {
    }

    @Override
    public void simpleGetEntityCount(Integer chId) {
    }

    @Override
    public void simpleQueryChannelTag() {
    }

    @Override
    public List<User> selectUser(User param) {
        return null;
    }
}
