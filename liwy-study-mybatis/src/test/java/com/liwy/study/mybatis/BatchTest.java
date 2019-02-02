package com.liwy.study.mybatis;

import com.liwy.study.mybatis.dao.IContentDao;
import com.liwy.study.mybatis.entity.Content;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <b>名称：</b> 批量测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/30 15:19 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class BatchTest {
    private static final Logger logger = LoggerFactory.getLogger(BatchTest.class);
    private static SqlSessionFactory sqlSessionFactory;
    private SqlSession session;
    private IContentDao contentDao;
    private static List<Content> contentList;

    @BeforeClass
    public static void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        contentList = new ArrayList<>(5000);
        for(int i=0;i<5000;i++) {
            Content content = new Content();
            content.setChannelId(1000006);
            content.setUserId(1L);
            content.setTex("批量插入测试");
            content.setCreateTime(new Date());
            content.setCreateBy("wenyao02.li");
            content.setUpdateBy("wenyao.li");
            contentList.add(content);
        }
    }

    @After
    public void testAfter() {
        session.close();
    }

    @Test
    public void testBatchFor() {
        session = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
        contentDao = session.getMapper(IContentDao.class);
        long current = System.currentTimeMillis();
        for(Content content: contentList) {
            contentDao.insertContent(content);
        }
        System.out.println(System.currentTimeMillis() - current);
    }

    @Test
    public void testBatchBatch() {
        session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        contentDao = session.getMapper(IContentDao.class);
        long current = System.currentTimeMillis();
        for(Content content: contentList) {
            contentDao.insertContent(content);
        }
        System.out.println(System.currentTimeMillis() - current);
    }

    @Test
    public void testBatchXml() {
//        mysql默认接受sql的大小是1048576(1M)
        session = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
        contentDao = session.getMapper(IContentDao.class);
        long current = System.currentTimeMillis();
        int num = contentDao.batchInsertContent(contentList);
        System.out.println(System.currentTimeMillis() - current);
        System.out.println(num);
        System.out.println(contentList);
    }

    @Test
    public void testBatchUpdate() {
//        mysql默认接受sql的大小是1048576(1M)
//        注意在url中添加allowMultiQueries=true
        List<Content> contents = new ArrayList<>(1000);
        for(int i=0;i<1000;i++) {
            Content content = new Content();
            content.setId((long) i);
            content.setChannelId(1000006);
            content.setUserId(1L);
            content.setTex("批量更新测试");
            content.setCreateTime(new Date());
            content.setCreateBy("wenyao02.li");
            content.setUpdateBy("wenyao.li");
            contents.add(content);
        }
        session = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
        contentDao = session.getMapper(IContentDao.class);
        long current = System.currentTimeMillis();
        int num = contentDao.batchUpdateContent(contents);
        System.out.println(System.currentTimeMillis() - current);
        System.out.println(num);
    }

    @Test
    public void testBatchUpdateFor() {
//        mysql默认接受sql的大小是1048576(1M)
//        注意在url中添加allowMultiQueries=true
        List<Content> contents = new ArrayList<>(1000);
        for(int i=0;i<1000;i++) {
            Content content = new Content();
            content.setId((long) i);
            content.setChannelId(1000006);
            content.setUserId(1L);
            content.setTex("批量更新测试");
            content.setCreateTime(new Date());
            content.setCreateBy("wenyao02.li");
            content.setUpdateBy("wenyao.li");
            contents.add(content);
        }
        session = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
        contentDao = session.getMapper(IContentDao.class);
        long current = System.currentTimeMillis();
        for(Content content: contents) {
            Content param = new Content();
            param.setId(content.getId());
            contentDao.updateContent(content, param);
        }
        System.out.println(System.currentTimeMillis() - current);
    }
}
