package com.liwy.study.orm.mybatis;

import com.liwy.study.orm.mybatis.bo.ChannelBo;
import com.liwy.study.orm.mybatis.bo.ContentBo;
import com.liwy.study.orm.mybatis.dao.IChannelDao;
import com.liwy.study.orm.mybatis.dao.IContentDao;
import com.liwy.study.orm.mybatis.entity.Content;
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
public class DynamicSqlTest {
    private static final Logger logger = LoggerFactory.getLogger(DynamicSqlTest.class);
    private static SqlSessionFactory sqlSessionFactory;
    private SqlSession session;
    private IContentDao contentDao;
    private IChannelDao channelDao;

    @BeforeClass
    public static void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Before
    public void testBefore() {
        session = sqlSessionFactory.openSession();
        contentDao = session.getMapper(IContentDao.class);
        channelDao = session.getMapper(IChannelDao.class);
    }

    @After
    public void testAfter() {
        session.close();
    }

    @Test
    public void testSql() {
        ChannelBo channelBo = channelDao.getChannelBo(1000005);
        System.out.println(channelBo);
    }

    @Test
    public void testIf() throws InterruptedException {
        Content content = new Content();
        content.setTex("更新数据");
        Content param = new Content();
        param.setId(1L);
        contentDao.updateContent(content, param);
    }

    @Test
    public void testChoose() {
        ContentBo param = new ContentBo();
        param.setChannelId(1000005);
        param.setUserId(2L);
        contentDao.selectContent(param, null);
    }

    @Test
    public void testWhere() {
        Content content = new Content();
        content.setTex("更新数据1");
        Content param = new Content();
        param.setId(2L);
        contentDao.updateContent(content, param);
    }

    @Test
    public void testSet() {
        Content content = new Content();
        content.setTex("更新数据2");
        Content param = new Content();
        param.setId(3L);
        contentDao.updateContent(content, param);
    }

    @Test
    public void testTrim() {
        Content content = new Content();
        content.setChannelId(1000006);
        content.setUserId(1L);
        content.setTex("插入测试");
        content.setCreateTime(new Date());
        content.setCreateBy("wenyao02.li");
        contentDao.insertContent(content);
    }

    @Test
    public void testForeach() {
        ContentBo param = new ContentBo();
        param.setIdList(Arrays.asList(1L, 2L, 3L));
        contentDao.selectContent(param, null);
    }

    @Test
    public void testBind() {
        ContentBo param = new ContentBo();
        param.setCreateBy("wenyao");
        param.setUpdateBy("wenyao");
        contentDao.selectContent(param, null);
    }

    @Test
    public void testSelectKey() {
        Content content = new Content();
        content.setChannelId(1000006);
        content.setUserId(1L);
        content.setTex("插入测试");
        content.setCreateTime(new Date());
        content.setCreateBy("wenyao02.li");
        contentDao.insertContentAndId(content);
    }
}
