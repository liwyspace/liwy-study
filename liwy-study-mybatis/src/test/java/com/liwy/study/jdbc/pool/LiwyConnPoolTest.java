package com.liwy.study.jdbc.pool;

import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/1/16 16:20 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class LiwyConnPoolTest {
    LiwyConnPool pool = new LiwyConnPoolImpl1();
    LiwyConnPool pool2 = new LiwyConnPoolImpl2();
    LiwyConnPool pool3 = new LiwyConnPoolImpl3();

    @Test
    public void testLiwyConnPoolImpl1() throws InterruptedException {
        Thread[] threads = new Thread[30];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection con = pool.getConnection();
                        System.out.println(Thread.currentThread().getName()
                                + ": 从连接池取出一个连接" + con);
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("insert into liwy_user (username, email) VALUES ('LIWY','liwy@116.com')");

                        // 释放相关资源
                        stmt.close();
                        pool.releaseConnection(con);
                        System.out.println(Thread.currentThread().getName() + ": 释放连接"
                                + con);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
            Thread.sleep(300);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        pool.close();
    }

    @Test
    public void testLiwyConnPoolImpl2() throws InterruptedException {
        Thread[] threads = new Thread[30];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection con = pool2.getConnection();
                        System.out.println(Thread.currentThread().getName()
                                + ": 从连接池取出一个连接" + con);
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("insert into liwy_user (username, email) VALUES ('LIWY','liwy@116.com')");

                        // 释放相关资源
                        stmt.close();
                        con.close();
                        System.out.println(Thread.currentThread().getName() + ": 释放连接"
                                + con);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
            Thread.sleep(300);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        pool2.close();
    }

    @Test
    public void testLiwyConnPoolImpl3() throws InterruptedException {
        Thread[] threads = new Thread[30];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection con = pool3.getConnection();
                        System.out.println(Thread.currentThread().getName()
                                + ": 从连接池取出一个连接" + con);
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("insert into liwy_user (username, email) VALUES ('LIWY','liwy@116.com')");

                        // 释放相关资源
                        stmt.close();
                        con.close();
                        System.out.println(Thread.currentThread().getName() + ": 释放连接"
                                + con);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
            Thread.sleep(300);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        pool3.close();
    }

    /**
     * <b>描述：</b> 通过DataSource接口访问连接池，保证Java应用与连接池之间的独立性<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = new LiwyDataSource();
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}
