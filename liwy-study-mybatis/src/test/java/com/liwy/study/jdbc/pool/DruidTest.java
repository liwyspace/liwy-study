package com.liwy.study.jdbc.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/1/17 14:44 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class DruidTest {
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
    public void testDruid() {
        String driver = pro.getProperty("dev_db_driver");
        String url = pro.getProperty("dev_db_url");
        String user = pro.getProperty("dev_db_username");
        String password = pro.getProperty("dev_db_password");

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(password);

        Connection conn = null;
        try {
            conn = druidDataSource.getConnection();
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testDruidByProperties() throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(DbcpTest.class.getResourceAsStream("/jdbc/druid.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
