package com.liwy.study.orm.mybatis;

import com.liwy.study.orm.mybatis.bo.ChannelBo;
import com.liwy.study.orm.mybatis.bo.ContentBo;
import com.liwy.study.orm.mybatis.bo.SexEnum;
import com.liwy.study.orm.mybatis.dao.IChannelDao;
import com.liwy.study.orm.mybatis.dao.IContentDao;
import com.liwy.study.orm.mybatis.dao.IUserDao;
import com.liwy.study.orm.mybatis.entity.Content;
import com.liwy.study.orm.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

/**
 * <b>名称：</b> 动态sql测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/29 19:22 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class TransactionTest {
    private static final Logger logger = LoggerFactory.getLogger(TransactionTest.class);
    private static SqlSessionFactory sqlSessionFactory;
    private SqlSession session;
    private IUserDao userDao;
    private IContentDao contentDao;

    @BeforeClass
    public static void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * <b>描述：</b> mybatis默认不自动提交（事务）<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testTransaction() {
        session = sqlSessionFactory.openSession(false); // 默认为false
        userDao = session.getMapper(IUserDao.class);

        try {
            User user = new User();
            user.setUsername("文姚");
            user.setSex(SexEnum.BOY);
            user.setEmail("liwenyao5@126.com");
            userDao.insertUser(user);
            User user1 = new User();
            user1.setUsername("文姚");
//            user1.setSex(SexEnum.BOY);
            user1.setEmail("liwenyao5@126.com");
            userDao.insertUser(user1);

            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * <b>描述：</b> mybatis开启自动提交（无事务）<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testNoTransaction() {
        session = sqlSessionFactory.openSession(true); // 默认为false
        userDao = session.getMapper(IUserDao.class);

        try {
            User user = new User();
            user.setUsername("文姚");
            user.setSex(SexEnum.BOY);
            user.setEmail("liwenyao5@126.com");
            userDao.insertUser(user);
            User user1 = new User();
            user1.setUsername("文姚");
//            user1.setSex(SexEnum.BOY);
            user1.setEmail("liwenyao5@126.com");
            userDao.insertUser(user1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
