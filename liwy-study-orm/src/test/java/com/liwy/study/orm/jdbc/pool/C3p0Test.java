package com.liwy.study.orm.jdbc.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <b>名称：</b> C3p0数据库连接池测试<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/1/17 14:43 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class C3p0Test {

    @Test
    public void testComboPooled() throws PropertyVetoException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://127.0.0.1/liwy_study_mybatis?allowMultiQueries=true" );
        cpds.setUser("root");
        cpds.setPassword("l332301842");

        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);

        Connection conn = null;
        try {
            conn =  cpds.getConnection();
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

    /**
     * <b>描述：</b> 使用DataSource接口访问<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testDataSources() throws PropertyVetoException, SQLException {
        Properties properties = new Properties();
        try {
            properties.load(DbcpTest.class.getResourceAsStream("/jdbc/c3p0.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataSource unpooledDataSource = DataSources.unpooledDataSource("jdbc:mysql://127.0.0.1/liwy_study_mybatis?allowMultiQueries=true", "root", "l332301842");
        DataSource dataSources = DataSources.pooledDataSource(unpooledDataSource, properties);

        Connection conn = null;
        try {
            conn =  dataSources.getConnection();
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

    /**
     * <b>描述：</b> 通过c3p0-config.xml配置文件加载连接池<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testConfigXml() throws SQLException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql_pool");
        Connection conn = null;
        try {
            conn =  dataSource.getConnection();
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
