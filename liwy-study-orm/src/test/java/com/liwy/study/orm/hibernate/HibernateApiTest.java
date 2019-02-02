package com.liwy.study.orm.hibernate;

import com.liwy.study.orm.hibernate.entity.Channel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * <b>名称：</b> Hibernate API测试类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/1/17 16:59 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class HibernateApiTest {
    private SessionFactory sessionFactory;

    /**
     * 获取SessionFactory
     */
    @Before
    public void testBefore() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate/hibernate.cfg.xml").build();
        sessionFactory = new MetadataSources(registry).buildMetadata()
                .buildSessionFactory();
    }

    @After
    public void testAfter() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void testHibernate() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();// 开启事务

        // 保存实体
        Channel channel = new Channel();
        channel.setName("图片");
        channel.setPriority(5);
        session.save(channel);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        // 通过HQL查询
        List<Channel> result = session.createQuery("from Channel").list();
        for (Channel event : result) {
            System.out.println(event.getName());
        }

        session.getTransaction().commit();
        session.close();

    }
}
