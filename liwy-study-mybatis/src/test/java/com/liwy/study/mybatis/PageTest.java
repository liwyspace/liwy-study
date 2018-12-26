package com.liwy.study.mybatis;

import com.liwy.study.mybatis.bo.ContentBo;
import com.liwy.study.mybatis.bo.Pageable;
import com.liwy.study.mybatis.dao.IContentDao;
import com.liwy.study.mybatis.entity.Content;
import com.liwy.study.mybatis.plugins.PagePlugin;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>名称：</b> 分页功能测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/30 17:48 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class PageTest {
    private static final Logger logger = LoggerFactory.getLogger(PageTest.class);
    private static SqlSessionFactory sqlSessionFactory;
    private SqlSession session;
    private IContentDao contentDao;

    @BeforeClass
    public static void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Before
    public void testBefore() {
        session = sqlSessionFactory.openSession();
        contentDao = session.getMapper(IContentDao.class);
    }

    @After
    public void testAfter() {
        session.close();
    }

    /**
     * <b>描述：</b> 内存分页<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testRowBounds() {
        Map<String, Object> params = new HashMap<>();
        ContentBo contentBo = new ContentBo();
        params.put("contentBo", contentBo);
        List<Content> contentList = session.selectList("com.liwy.study.mybatis.dao.IContentDao.selectContent", params, new RowBounds(0, 20));
        logger.info(String.valueOf(contentList.size()));
    }

    /**
     * <b>描述：</b> SQL分页<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testSqlLimit() {
        ContentBo contentBo = new ContentBo();
        Pageable pageable = new Pageable();
        pageable.setOffset(1);
        pageable.setPageSize(50);
        List<Content> contentList = contentDao.selectContent(contentBo, pageable);
        logger.info(String.valueOf(contentList.size()));
    }

    /**
     * <b>描述：</b> 分页插件<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testPagePlugin() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory1 = new SqlSessionFactoryBuilder().build(inputStream);

        // 配置分页插件
        Configuration configuration = sqlSessionFactory1.getConfiguration();
        configuration.addInterceptor(new PagePlugin());

        SqlSession session1 = sqlSessionFactory1.openSession();
        IContentDao contentDao1 = session1.getMapper(IContentDao.class);
        ContentBo contentBo = new ContentBo();
        Pageable pageable = new Pageable();
        pageable.setOffset(1);
        pageable.setPageSize(30);
        List<Content> contentList = contentDao1.selectContent(contentBo, pageable);
        logger.info(String.valueOf(contentList.size()));
    }
}
