package com.liwy.study.orm.mybatis;

import com.liwy.study.orm.mybatis.dao.IContentDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * <b>名称：</b> mybatis日志测试类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/29 10:55 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class LoggerTest {

    /**
     * <b>描述：</b> <br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testLogger() throws IOException {
        // Mybatis 的内置日志工厂提供日志功能，内置日志工厂将日志交给以下其中一种工具作代理：
        // SLF4J
        // Apache Commons Logging
        // Log4j 2
        // Log4j
        // JDK logging
        // MyBatis 内置日志工厂基于运行时自省机制选择合适的日志工具。它会使用第一个查找得到的工具（按上文列举的顺序查找）。如果一个都未找到，日志功能就会被禁用。
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            IContentDao contentDao = session.getMapper(IContentDao.class);
            System.out.println(contentDao.getContent(1L));
        } finally {
            session.close();
        }
    }
}
