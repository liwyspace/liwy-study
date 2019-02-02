package com.liwy.study.orm.jdbc;

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
    /**
     * <b>描述：</b> 基础jdbc流程<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testJdbc() throws IOException {
        Properties properties = new Properties();
        properties.load(JdbcTest.class.getResourceAsStream("/config-jdbc.properties"));

        try {
            // 1. 加载注册驱动
            Class.forName(properties.getProperty("dev_db_driver"));
            // 输出jdbc日志
            DriverManager.setLogWriter(new PrintWriter(System.out, true));

            // 2. 创建数据库连接
            String url = properties.getProperty("dev_db_url");
            String user = properties.getProperty("dev_db_username");
            String pwd = properties.getProperty("dev_db_password");
            Connection connection = DriverManager.getConnection(url, user, pwd);

            try {
                // 3. 创建Statement对象
                Statement statement = connection.createStatement();

                // 4. 执行更新sql
                String updateSql = "INSERT INTO liwy_content(channel_id,user_id,tex,create_by,create_time) VALUES (1000006,1,'JDBC第一篇文章','wenyao02.li','2018-11-28 16:49:22')";
                int num = statement.executeUpdate(updateSql);
                System.out.println("成功插入" + num + "条数据！");

                // 5. 执行查询sql
                String querySql = "SELECT * FROM liwy_user";
                ResultSet resultSet = statement.executeQuery(querySql);
                // 5.1 打印结果集
                while (resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    Byte sex = resultSet.getByte("sex");
                    String email = resultSet.getString("email");
                    System.out.println("id=" + id + ",name=" + name + ",sex="
                            + sex + ",email=" + email);
                }

                // 6. 删除新增加的记录
                num = statement.executeUpdate("DELETE FROM liwy_content WHERE channel_id=1000006 AND user_id=1 AND tex='JDBC第一篇文章'");
                System.out.println("成功删除" + num + "条数据！");

                // 7. 关闭连接
                resultSet.close();
                statement.close();
            } finally {
                // 8. 关闭连接
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
            // 输出jdbc日志
            DriverManager.setLogWriter(new PrintWriter(System.out, true));

            // 2. 创建数据库连接
            String url = properties.getProperty("dev_db_url");
            String user = properties.getProperty("dev_db_username");
            String pwd = properties.getProperty("dev_db_password");
            Connection connection = DriverManager.getConnection(url, user, pwd);
            try {
                // 3. 创建Statement对象
                Statement statement = connection.createStatement();

                // 4. 执行sql
                String sql = "INSERT INTO liwy_content(channel_id,user_id,tex,create_by,create_time) VALUES (1000006,1,'JDBC第一篇文章','wenyao02.li','2018-11-28 16:49:22')";
                int num = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                System.out.println("成功插入" + num + "条数据！");

                // 5. 获取自动生成的主键
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("主键：" + rs.getLong(1));
                }

                // 关闭连接
                rs.close();
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
     * <p>
     *      Statement对象的executeQuery()方法返回的ResultSet对象中不会立即存放所有数据，
     *      只有当程序遍历ResultSet时，ResultSet对象才会到数据库中抓取相应的数据。
     * </p>
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
            DriverManager.setLogWriter(new PrintWriter(System.out, true));

            // 2. 创建数据库连接
            String url = properties.getProperty("dev_db_url");
            String user = properties.getProperty("dev_db_username");
            String pwd = properties.getProperty("dev_db_password");
            Connection connection = DriverManager.getConnection(url, user, pwd);

            try {
                // 3. 创建Statement对象
                Statement statement = connection.createStatement();
//                statement.setFetchSize(20); // 在Statement上设置

                // 4. 执行sql
                ResultSet resultSet = statement.executeQuery("SELECT * FROM liwy_user");
                // 设置批量抓取条数
                resultSet.setFetchSize(20);
                while (resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    Byte sex = resultSet.getByte("sex");
                    String email = resultSet.getString("email");
                    System.out.println("id=" + id + ",name=" + name + ",sex="
                            + sex + ",email=" + email);
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
     * <b>描述：</b> 调用存储过程<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * <pre>
     *     CREATE DEFINER=`root`@`localhost` PROCEDURE `procGetEntityCount`(IN channelId INTEGER, OUT contentNum BIGINT, OUT tagNum BIGINT)
     *     BEGIN
     *     SELECT COUNT(*) FROM liwy_content m WHERE m.channel_id = channelId INTO contentNum;
     *     SELECT COUNT(*) FROM liwy_tag g INTO tagNum;
     *     END
     * </pre>
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
            DriverManager.setLogWriter(new PrintWriter(System.out, true));

            // 2. 创建数据库连接
            String url = properties.getProperty("dev_db_url");
            String user = properties.getProperty("dev_db_username");
            String pwd = properties.getProperty("dev_db_password");
            Connection connection = DriverManager.getConnection(url, user, pwd);

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
