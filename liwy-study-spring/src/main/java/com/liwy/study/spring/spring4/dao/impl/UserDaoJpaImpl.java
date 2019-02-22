package com.liwy.study.spring.spring4.dao.impl;

import com.liwy.study.spring.spring4.bean.User;
import com.liwy.study.spring.spring4.dao.IUserDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/2 17:41 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Repository
public class UserDaoJpaImpl implements IUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long getUserCount() {
        return null;
    }

    @Override
    public List<User> queryUserList() {
        Query query = entityManager.createQuery("select u from User u where u.id <= ?");
        query.setParameter(1, 20L);
        List<User> list = query.getResultList();
        return list;
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
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
        entityManager.persist(user);
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
