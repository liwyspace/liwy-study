package com.liwy.study.jdbc.pool;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <b>名称：</b> DBCP连接池测试类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/1/17 14:42 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class DbcpTest {
    /**
     * 加载类时，加载jdbc参数
     */
    private static Properties pro;

    static {
        pro = new Properties();
        try {
            pro.load(DbcpTest.class.getResourceAsStream("/config-jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBasicDataSource() {
        String driver = pro.getProperty("dev_db_driver");
        String url = pro.getProperty("dev_db_url");
        String user = pro.getProperty("dev_db_username");
        String password = pro.getProperty("dev_db_password");

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);

        DataSource dataSource = ds;

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            System.out.println(conn);
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch(Exception e) { }
        }
    }

    @Test
    public void testBasicDataSourceByProperties() throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(DbcpTest.class.getResourceAsStream("/jdbc/dbcp.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            System.out.println(conn);
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch(Exception e) { }
        }
    }
}
