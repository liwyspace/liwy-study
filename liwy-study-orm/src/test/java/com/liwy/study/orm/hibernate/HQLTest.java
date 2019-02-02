package com.liwy.study.orm.hibernate;

import com.liwy.study.orm.hibernate.entity.Channel;
import com.liwy.study.orm.hibernate.entity.Content;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/1/17 17:22 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class HQLTest {

    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeClass
    public static void testBefore() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate/hibernate.cfg.xml").build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
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

//        testSave();
    }

    @After
    public void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    /**
     * 保存数据
     */
    @Test
    public void testSave() {
        session.beginTransaction();
        Channel c1 = new Channel();
        c1.setName("美团");
        c1.setPriority(5);

        Content o1 = new Content();
        o1.setUserId(1L);
        o1.setCreateBy("wenyao02.li");
        o1.setChannel(c1);
        Content o2 = new Content();
        o2.setUserId(1L);
        o2.setCreateBy("wenyao03.li");
        o2.setChannel(c1);

        c1.getContentList().add(o1);
        c1.getContentList().add(o2);
        session.save(c1);

        session.getTransaction().commit();
    }
}
