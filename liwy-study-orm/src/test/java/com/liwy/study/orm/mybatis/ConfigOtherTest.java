package com.liwy.study.orm.mybatis;

import com.liwy.study.orm.mybatis.bo.SexEnum;
import com.liwy.study.orm.mybatis.dao.IContentDao;
import com.liwy.study.orm.mybatis.dao.IUserDao;
import com.liwy.study.orm.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * <b>名称：</b> 配置文件properties元素测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/29 10:51 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class ConfigOtherTest {
    private final Logger logger = LoggerFactory.getLogger(ConfigOtherTest.class);

    /**
     * <b>描述：</b> 配置参数加载顺序<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <p>
     * 1. 先加载properties 标签内的属性
     * 2. 再加载外部properties文件内的属性，覆盖之前属性
     * 3. 最后加载properties参数的属性，覆盖之前属性
     * </p>
     *
     * @param
     * @return void
     */
    @Test
    public void testProperties() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            logger.info(contentDao.getContent(1L).toString());
        } finally {
            session.close();
        }
    }

    /**
     * <b>描述：</b> 通过properties参数设置<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testPropertiesInJava() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        // 自定义Properties
        Properties properties = new Properties();
        properties.setProperty("dev_db_password", "l332301842");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
        SqlSession session = sqlSessionFactory.openSession();

        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            logger.info(contentDao.getContent(1L).toString());
        } finally {
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试自定义类型处理器<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testTypeHandler() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IUserDao userDao = session.getMapper(IUserDao.class);
            User user = new User();
            user.setEmail("tianshi@126.com");
            user.setUsername("天使");
            user.setSex(SexEnum.GIRL);
            user.setRegisterTime(new Date());
            userDao.insertUser(user);
            session.commit();
            logger.info(userDao.getUser(user.getId()).toString());
            User param = new User();
            param.setSex(SexEnum.BOY);
            List<User> userList = userDao.selectUser(param);
            logger.info(userList.toString());
        } finally {
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试自定义对象工厂<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <p>
     * MyBatis 每次创建结果对象的新实例时，它都会使用一个对象工厂（ObjectFactory）实例来完成。
     * 默认的对象工厂需要做的仅仅是实例化目标类，要么通过默认构造方法，要么在参数映射存在的时候通过参数构造方法来实例化。
     * 如果想覆盖对象工厂的默认行为，则可以通过创建自己的对象工厂来实现。
     * </p>
     *
     * @param
     * @return void
     */
    @Test
    public void testObjectFactory() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            logger.info(contentDao.getContentBo(1L).toString());
            logger.info(contentDao.getContentBo(1L).getCopyRight());
        } finally {
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试Plugin<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testPlugin() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            logger.info(contentDao.getContentBo(1L).toString());
        } finally {
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试使用不同环境的数据源<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testEnvironment() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "production");
        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            logger.info(contentDao.getContentBo(1L).toString());
        } finally {
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试根据不同的厂商执行不同的语句<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <p>
     * MyBatis 可以根据不同的数据库厂商执行不同的语句，这种多厂商的支持是基于映射语句中的 databaseId 属性。
     * MyBatis 会加载不带 databaseId 属性和带有匹配当前数据库 databaseId 属性的所有语句。
     * 如果同时找到带有 databaseId 和不带 databaseId 的相同语句，则后者会被舍弃。
     * </p>
     *
     * @param
     * @return void
     */
    @Test
    public void testDatabaseIdProvider() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IUserDao userDao = session.getMapper(IUserDao.class);
            logger.info(userDao.getUser(1L).toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            // 关闭Session
            session.close();
        }
    }
}
