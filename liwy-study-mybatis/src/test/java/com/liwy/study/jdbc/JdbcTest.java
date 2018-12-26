package com.liwy.study.jdbc;

import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Properties;

/**
 * <b>名称：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/12/4 14:05 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class JdbcTest {
    @Test
    public void testJdbc() throws IOException {
        Properties properties = new Properties();
        properties.load(JdbcTest.class.getResourceAsStream("/config-jdbc.properties"));

        try {
            // 1. 加载注册驱动
            Class.forName(properties.getProperty("dev_db_driver"));
            //输出jdbc日志
            DriverManager.setLogWriter(new PrintWriter(System.out,true));
            // 2. 创建数据库连接
            Connection connection = DriverManager.getConnection(properties.getProperty("dev_db_url"), properties.getProperty("dev_db_username"), properties.getProperty("dev_db_password"));
            try {
                // 3. 创建Statement对象
                Statement statement = connection.createStatement();

                // 4. 执行sql
                int num = statement.executeUpdate("INSERT INTO liwy_content(channel_id,user_id,tex,create_by,create_time) VALUES (1000006,1,'JDBC第一篇文章','wenyao02.li','2018-11-28 16:49:22')");
                System.out.println(num);
                ResultSet resultSet = statement.executeQuery("SELECT * FROM liwy_user");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
                    System.out.println(resultSet.getByte("sex"));
                }

                // 关闭连接
                resultSet.close();
                statement.close();
            } finally {
                // 关闭连接
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>描述：</b> 获取自动生成主键<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testGeneratedKeys() throws IOException {
        Properties properties = new Properties();
        properties.load(JdbcTest.class.getResourceAsStream("/config-jdbc.properties"));

        try {
            // 1. 加载注册驱动
            Class.forName(properties.getProperty("dev_db_driver"));
            //输出jdbc日志
            DriverManager.setLogWriter(new PrintWriter(System.out,true));
            // 2. 创建数据库连接
            Connection connection = DriverManager.getConnection(properties.getProperty("dev_db_url"), properties.getProperty("dev_db_username"), properties.getProperty("dev_db_password"));
            try {
                // 3. 创建Statement对象
                Statement statement = connection.createStatement();

                // 4. 执行sql
                String sql = "INSERT INTO liwy_content(channel_id,user_id,tex,create_by,create_time) VALUES (1000006,1,'JDBC第一篇文章','wenyao02.li','2018-11-28 16:49:22')";
                int num = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                System.out.println(num);
                // 获取自动生成的主键
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()) {
                    System.out.println(rs.getLong(1));
                }

                // 关闭连接
                statement.close();
            } finally {
                // 关闭连接
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>描述：</b> 批量抓取<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testFetchSize() throws IOException {
        Properties properties = new Properties();
        properties.load(JdbcTest.class.getResourceAsStream("/config-jdbc.properties"));

        try {
            // 1. 加载注册驱动
            Class.forName(properties.getProperty("dev_db_driver"));
            //输出jdbc日志
            DriverManager.setLogWriter(new PrintWriter(System.out,true));
            // 2. 创建数据库连接
            Connection connection = DriverManager.getConnection(properties.getProperty("dev_db_url"), properties.getProperty("dev_db_username"), properties.getProperty("dev_db_password"));
            try {
                // 3. 创建Statement对象
                Statement statement = connection.createStatement();
//                statement.setFetchSize(20);

                // 4. 执行sql
                ResultSet resultSet = statement.executeQuery("SELECT * FROM liwy_user");
                resultSet.setFetchSize(20);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
                    System.out.println(resultSet.getByte("sex"));
                }

                resultSet.close();
                // 关闭连接
                statement.close();
            } finally {
                // 关闭连接
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>描述：</b> 调用存储过程<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testProcedure() throws IOException {
        Properties properties = new Properties();
        properties.load(JdbcTest.class.getResourceAsStream("/config-jdbc.properties"));

        try {
            // 1. 加载注册驱动
            Class.forName(properties.getProperty("dev_db_driver"));
            //输出jdbc日志
            DriverManager.setLogWriter(new PrintWriter(System.out,true));
            // 2. 创建数据库连接
            Connection connection = DriverManager.getConnection(properties.getProperty("dev_db_url"), properties.getProperty("dev_db_username"), properties.getProperty("dev_db_password"));
            try {
                // 3. 创建Statement对象
                CallableStatement statement = connection.prepareCall("{call procGetEntityCount(?, ?, ?)}");
                statement.setInt(1, 1000006);
                statement.registerOutParameter(2, Types.BIGINT);
                statement.registerOutParameter(3, Types.BIGINT);
                statement.execute();

                System.out.println(statement.getLong(2));
                System.out.println(statement.getLong(3));

                // 关闭连接
                statement.close();
            } finally {
                // 关闭连接
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
