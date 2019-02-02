package com.liwy.study.hibernate;

import com.liwy.study.hibernate.entity.Channel;
import com.liwy.study.hibernate.entity.Content;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/1/17 18:00 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class SQLTest {
    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeClass
    public static void testBefore() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate/hibernate.cfg.xml").build();
        sessionFactory = new MetadataSources(registry).buildMetadata()
                .buildSessionFactory();
    }

    @AfterClass
    public static void testAfter() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Before
    public void startSession() {
        session = sessionFactory.openSession();
    }

    @After
    public void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testList() {
        String sql = "select * from liwy_channel";
        NativeQuery<Channel> query = session
                .createNativeQuery(sql, Channel.class);
        List<Channel> list = query.getResultList();
        for (Channel order : list) {
            System.out.println(order.toString());
        }
    }

    /**
     * <b>描述：</b> 分页查询<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testPage() {
        String sql = "select * from liwy_content";
        // setFirstResult()从0开始
        Query<Content> query = session.createNativeQuery(sql, Content.class);
        query.setFirstResult(1).setMaxResults(4);
        List<Content> list = query.getResultList();
        for (Content content : list) {
            System.out.println(content.toString());
        }
    }

    // 多条件查询，返回List集合(第一种形式：索引占位符)
    @Test
    public void testQueryIndex() {
        String sql = "select * from liwy_content where user_id=? and create_by=?";
        Query<Content> query = session.createNativeQuery(sql, Content.class);
        query.setParameter(1, 2);
        query.setParameter(2, "wenyao02.li");

        List<Content> list = query.getResultList();
        for (Content order : list) {
            System.out.println(order.toString());
        }
    }

    // 多条件查询，返回List集合(第二种形式：命名占位符)
    @Test
    public void testQueryName() {
        String sql = "select * from liwy_content where user_id=:userId and create_by=:createBy";
        Query<Content> query = session.createNativeQuery(sql, Content.class);
        query.setParameter("userId", 2);
        query.setParameter("createBy", "wenyao02.li");

        List<Content> list = query.getResultList();
        for (Content order : list) {
            System.out.println(order.toString());
        }
    }

    // 更新操作
    @Test
    public void testUpdate() {
        String sql = "update liwy_content set user_id = ? where id = ?";
        session.beginTransaction();
        Query<?> query = session.createNativeQuery(sql);
        query.setParameter(1, 3);
        query.setParameter(2, 3);
        int i = query.executeUpdate();
        System.out.println(i);
        session.getTransaction().commit();
    }

    // 删除操作
    @Test
    public void testDelete() {
        String sql = "delete from liwy_content where id in (:idList)";
        session.beginTransaction();
        List<Integer> list = Arrays.asList(7,8);
        Query<?> query = session.createNativeQuery(sql).setParameter("idList",
                list);
        int i = query.executeUpdate();
        System.out.println(i);
        session.getTransaction().commit();
        session.close();
    }

    // 获取某一列的值
    @Test
    public void testSingleValue() {
        String sql = "select create_by from liwy_content";
        Query<String> query = session.createNativeQuery(sql);
        query.setFirstResult(0).setMaxResults(20);
        List<String> list = query.getResultList();
        for (String o : list) {
            System.out.println(o);
        }
    }

    // 使用对象的方式封装结果数据
    @Test
    public void testObject() {
        String sql = "select o.* from liwy_content o left join liwy_channel c on o.channel_id = c.id where o.id = ?";
        Query<Content> query = session.createNativeQuery(sql, Content.class);
        query.setParameter(1, 1);
        List<Content> list = query.getResultList();
        for (Content o : list) {
            System.out.println(o.toString());
        }
    }

    // 多列数据的查询
    @Test
    public void testObjectArray() {
        String sql = "select o.create_by, c.name from liwy_content o left join liwy_channel c on o.channel_id = c.id where o.id = ?";
        Query<Object[]> query = session.createNativeQuery(sql);
        query.setParameter(1, 1);

        List<Object[]> list = query.getResultList();
        for (Object[] o : list) {
            System.out.println(o[0] + ";;" + o[1] + ";;");
        }
    }

    // 函数查询
    @Test
    public void testFunction() {
        String sql = "select max(id), count(*) from liwy_content";
        Query<Object[]> query = session.createNativeQuery(sql);
        Object[] obj = query.getSingleResult();
        System.out.println(obj[0] + "::" + obj[1] + "::");
    }

}
