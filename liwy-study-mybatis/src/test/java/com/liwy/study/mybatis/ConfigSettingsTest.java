package com.liwy.study.mybatis;

import com.liwy.study.mybatis.bo.ContentBo;
import com.liwy.study.mybatis.dao.IContentDao;
import com.liwy.study.mybatis.entity.Content;
import com.mysql.jdbc.exceptions.jdbc4.MySQLTimeoutException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.AutoMappingUnknownColumnBehavior;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.JdbcType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <b>名称：</b> 配置文件settings元素测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/29 10:51 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class ConfigSettingsTest {

    Logger logger = LoggerFactory.getLogger(ConfigSettingsTest.class);

    /**
     * <b>描述：</b> Settings配置项测试-缓存<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <p>
     * localCacheScope 配置一级缓存
     * cacheEnabled 配置二级缓存
     * 注：Mybatis的一级缓存最大范围是SqlSession内部，有多个SqlSession或者分布式的环境下，有操作数据库写的话，会引起脏数据，
     * 建议是把一级缓存的默认级别设定为Statement，即不使用一级缓存。
     * 使用二级缓存需要在Mapper中配置cache标签，且缓存实体类需要序列化。
     * </p>
     *
     * @param
     * @return void
     */
    @Test
    public void testCatch() throws IOException, InterruptedException {
        Thread.sleep(1000);
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 覆盖配置信息
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setLocalCacheScope(LocalCacheScope.SESSION);
        configuration.setCacheEnabled(true);

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();
        SqlSession session2 = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            // 测试一级缓存
            logger.info("第1次查询：" + contentDao.getContent(1L));
            logger.info("第2次查询：" + contentDao.getContent(1L));
            logger.info("第3次查询：" + contentDao.getContent(2L));
            // 二级缓存是从cache（mapper.xml中定义的cache）中取得，如果session不commit，那么，数据就不会放入cache中。所以，只有commit后，才能取得。
            session.commit();

            // 测试二级缓存
            IContentDao contentDao2 = session2.getMapper(IContentDao.class);
            logger.info("第4次查询：" + contentDao2.getContent(1L));
            logger.info("第5次查询：" + contentDao2.getContent(2L));

            // 测试多个Session并发操作时，一级缓存造成的脏数据
            logger.info("第6次查询：" + contentDao.getContent(1L));
            logger.info("第7次查询：" + contentDao2.getContent(1L));
            Content content = new Content();
            content.setTex("脏数据测试");
            Content param = new Content();
            param.setId(1L);
            contentDao2.updateContent(content, param);
            logger.info("第8次查询：" + contentDao.getContent(1L)); // 查询结果为脏数据
            logger.info("第9次查询：" + contentDao2.getContent(1L));
        } finally {
            // 关闭Session
            session.close();
            session2.close();
        }
    }

    /**
     * <b>描述：</b> Settings配置项测试-关联对象延迟加载<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <p>
     * lazyLoadingEnabled
     * aggressiveLazyLoading
     * lazyLoadTriggerMethods 指定哪些方法会出发懒加载
     * 延迟加载配置项：
     * | 加载策略       | lazyLoadingEnabled | aggressiveLazyLoading |
     * | 直接加载       |       false        |          false        |
     * | 深度延迟加载   |       true         |          false        |
     * | 侵入式延迟加载 |       true         |          true         |
     * </p>
     *
     * @param
     * @return void
     */
    @Test
    public void testLazyLoading() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            ContentBo contentBo = contentDao.getContentBo(1L);
            logger.info("------------加载时机分隔线--------------");
            logger.info(contentBo.toString());
            logger.info("------------加载时机分隔线--------------");
            logger.info(contentBo.getTex());
            logger.info(contentBo.getTagList().toString());
        } finally {
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 调用存储过程返回多结果集<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <pre>
     *   #创建一个返回多个结果集的存储过程
     *   DELIMITER //
     *   DROP PROCEDURE IF EXISTS procGetEntityCount//
     *   CREATE PROCEDURE procGetEntityCount (IN channelId INTEGER, OUT contentNum BIGINT, OUT tagNum BIGINT)
     *   BEGIN
     *   SELECT COUNT(*) FROM liwy_content m WHERE m.channel_id = channelId INTO contentNum;
     *   SELECT COUNT(*) FROM liwy_tag g INTO tagNum;
     *   END//
     * </pre>
     *
     * @param
     * @return void
     */
    @Test
    public void testMultipleResultSets() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            ContentBo contentBo = new ContentBo();
            contentDao.getEntityCount(1000005, contentBo);
            logger.info(contentBo.getContentNum().toString());
            logger.info(contentBo.getTagNum().toString());
            logger.info(contentBo.toString());
        } finally {
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试列标签<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <p>
     * 如果useColumnLabel的设置为false是sql中的as的列标签将无法映射到对象上
     * </p>
     *
     * @param
     * @return void
     */
    @Test
    public void testColumnLabel() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            logger.info(contentDao.getContent(1L).toString());
        } finally {
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试自增主键<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <p>
     * useGeneratedKeys为true时全局设置获取数据库中自动生成的主键，并通过keyProperty将获取的主键设置到对象的指定字段上。
     * </p>
     *
     * @param
     * @return void
     */
    @Test
    public void testGeneratedKeys() throws IOException, InterruptedException {
        Thread.sleep(1000);
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            Content content = new Content();
            content.setChannelId(1000006);
            content.setUserId(1L);
            content.setTex("插入测试");
            content.setCreateTime(new Date());
            content.setCreateBy("wenyao02.li");
            int num = contentDao.insertContent(content);
            logger.info(String.valueOf(num));
            logger.info(content.toString());
        } finally {
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试自动映射设置<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <p>
     * autoMappingBehavior
     * autoMappingUnknownColumnBehavior
     * </p>
     *
     * @param
     * @return void
     */
    @Test
    public void testAutoMapping() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 覆盖配置信息
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setAutoMappingBehavior(AutoMappingBehavior.PARTIAL);
        configuration.setAutoMappingUnknownColumnBehavior(AutoMappingUnknownColumnBehavior.WARNING);

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            logger.info(contentDao.getContent(1L).toString());
        } finally {
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试执行器<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <pre>
     *     执行器。默认：SIMPLE
     *     SIMPLE 就是普通的执行器；
     *     REUSE 执行器会重用预处理语句（prepared statements）；
     *     BATCH 执行器将重用语句并执行批量更新。-->
     * </pre>
     *
     * @param
     * @return void
     */
    @Test
    public void testExecutorType() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 覆盖配置信息
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setDefaultExecutorType(ExecutorType.BATCH);
        // 如果使用SIMPLE执行器，则不能启用useGeneratedKeys

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            Content content = new Content();
            content.setChannelId(1000007);
            content.setUserId(2L);
            content.setTex("插入测试");
            content.setCreateTime(new Date());
            content.setCreateBy("wenyao06.li");

            long t1 = System.currentTimeMillis();
            for (int i = 1; i < 1000; i++) {
                int num = contentDao.insertContent(content);
            }
            System.out.println(System.currentTimeMillis() - t1 + "ms");
        } finally {
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试超时时间<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testTimeout() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 覆盖配置信息
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setDefaultStatementTimeout(1);

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        long t1 = System.currentTimeMillis();
        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            ContentBo param = new ContentBo();
//            param.setId(1L);
            param.setCreateBy("wenyao");
            List<ContentBo> contentBoList = contentDao.selectContentBo(param);
            logger.info(contentBoList.get(0).toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            System.out.println(System.currentTimeMillis() - t1 + "ms");
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试开启下划线映射驼峰字段<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testMapUnderscoreToCamelCase() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 覆盖配置信息
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            Content content = contentDao.getContent(1L);
            logger.info(content.toString());
        } finally {
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试JdbcTypeForNull<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <p>
     * mysql-connector-java 5.1.40好像不需要， 后续使用ojdbc时再测试
     * </p>
     *
     * @param
     * @return void
     */
    @Test
    public void testJdbcTypeForNull() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 覆盖配置信息
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.OTHER);

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            Content content = new Content();
            content.setChannelId(1000006);
            content.setUserId(1L);
            content.setTex("插入测试");
            content.setCreateTime(new Date());
            content.setCreateBy("wenyao02.li");
            int num = contentDao.insertContent(content);
            logger.info(String.valueOf(num));
        } finally {
            // 关闭Session
            session.close();
        }
    }

    /**
     * <b>描述：</b> 测试返回null还是空对象<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <p>
     * 当返回的所有字段都是空时（注意该条记录是存在的，只是查询的字段是空），是返回null还是空对象
     * </p>
     *
     * @param
     * @return void
     */
    @Test
    public void testReturnInstanceForEmptyRow() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); // 从ClassPath中获取配置文件流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 覆盖配置信息
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setReturnInstanceForEmptyRow(true);

        // 获取Session
        SqlSession session = sqlSessionFactory.openSession();

        // 查询数据
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            Content content = contentDao.getContent(1L);
            Assert.assertTrue(Objects.nonNull(content));
        } finally {
            // 关闭Session
            session.close();
        }
    }
}
