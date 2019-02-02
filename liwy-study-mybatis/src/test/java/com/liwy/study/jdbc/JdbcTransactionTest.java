package com.liwy.study.jdbc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

/**
 * <b>名称：</b> JDBC事务管理<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/12/4 16:50 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class JdbcTransactionTest {
    private static final Logger log = LoggerFactory.getLogger(JdbcRowSetTest.class);

    /**
     * 加载类时，加载jdbc参数
     */
    private static Properties pro;

    static {
        pro = new Properties();
        try {
            pro.load(JdbcTest.class.getResourceAsStream("/config-jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据库连接
     *
     * @return
     * @throws SQLException
     */
    private Connection getConn() throws SQLException {
        String driver = pro.getProperty("dev_db_driver");
        String url = pro.getProperty("dev_db_url");
        String user = pro.getProperty("dev_db_username");
        String password = pro.getProperty("dev_db_password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //输出jdbc日志
        DriverManager.setLogWriter(new PrintWriter(System.out, true));

        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 测试事务与保存点
     *
     * <pre>
     * 事务的四种隔离级别：
     *     Connection.TRANSACTION_READ_UNCOMMITTED 读未提交 脏读 不可重复读 幻读
     *          允许读取其他事务的未提交数据，允许读取其他事务的已更新数据，允许读取其他事务的已新增数据
     *     Connection.TRANSACTION_READ_COMMITTED   读已提交      不可重复读 幻读
     *          不允许读取其他事务的未提交数据，允许读取其他事务的已更新数据，允许读取其他事务的已新增数据
     *     Connection.TRANSACTION_REPEATABLE_READ  可重复读                 幻读
     *          不允许读取其他事务的未提交数据，不允许读取其他事务的已更新数据，允许读取其他事务的已新增数据
     *     Connection.TRANSACTION_SERIALIZABLE     串行化
     *          不允许读取其他事务的未提交数据，不允许读取其他事务的已更新数据，不允许读取其他事务的已新增数据
     * </pre>
     */
    @Test
    public void testTransaction() {
        Connection con = null;
        Statement st = null;

        try {
            con = this.getConn();
            con.setAutoCommit(false); // 关闭自动提交

            st = con.createStatement();
            st.executeUpdate("update liwy_user set username='琛琛1' where id=10");
            Savepoint sp = con.setSavepoint();
            try {
                st.executeUpdate("update liwy_user set username='琛琛1' where id=11");
                st.executeUpdate("update liwy_user set username='琛琛1' where ids=12");
            } catch (Exception e) {
                con.rollback(sp);
            }

            con.commit();

            st.close();
            // con.releaseSavepoint(sp);
            con.setAutoCommit(true);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试批量更新
     */
    @Test
    public void testBatch() {
        try {
            Connection con = this.getConn();
            con.setAutoCommit(false);
            Date d1 = new Date();

            String sql = "insert into liwy_user(id,username) values(?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            int max = 200000;
            int count = 0;
            try {
                for (int i = 100000; i < max; i++) {
                    pst.setLong(1, i);
                    pst.setString(2, "liwy" + i);
                    pst.addBatch(); // 将其加入到批处理
                    count++;
                    if (count % 100 == 0 || count == max) {
                        pst.executeBatch();
                    }
                }
            } catch (Exception e) {
                con.rollback();
            }

            con.commit();

            pst.close();
            con.close();
            Date d2 = new Date();
            Long num = d2.getTime() - d1.getTime();
            System.out.println("插入完毕,共耗时：" + num + "毫秒");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
