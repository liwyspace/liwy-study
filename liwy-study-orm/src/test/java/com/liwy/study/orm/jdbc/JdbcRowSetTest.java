package com.liwy.study.orm.jdbc;

import com.sun.rowset.CachedRowSetImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.rowset.CachedRowSet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <b>名称：</b> 行集测试类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/12/4 16:52 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class JdbcRowSetTest {

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
     * 测试行集
     *
     * <p>RowSet接口继承了ResultSet接口，却无需始终保持与数据库的连接。</p>
     * <p>被缓存的行集有一个非常重要的有点：断开数据库连接后仍然可以使用行集。</p>
     */
    @Test
    public void testRowSet() {
        try {
            Connection con = this.getConn();

            String sql = "select id, username from liwy_user";
            PreparedStatement pst = con.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();

            // 缓存行集
            CachedRowSet crs = new CachedRowSetImpl();
            crs.populate(rs);

            // 关闭连接
            rs.close();
            pst.close();
            con.close();

            // 操作行集
            while (crs.next()) {
                String id = crs.getString(1);
                String uname = crs.getString(2);
                log.debug("NEXT:" + id + "/" + uname);
            }

            // 更新行
            crs.absolute(4);
            log.debug("更新行：" + crs.getRow());
            crs.updateString(2, "天使222");
            crs.updateRow();

            // 重新获取连接提交更新数据
            Connection conn = this.getConn();
            conn.setAutoCommit(false);
            crs.acceptChanges(conn); // 提交更新
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 行集可以自己创建连接
     */
    @Test
    public void testRowSet2() {
        String driver = pro.getProperty("dev_db_driver");
        String url = pro.getProperty("dev_db_url");
        String user = pro.getProperty("dev_db_username");
        String password = pro.getProperty("dev_db_password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            CachedRowSet crs = new CachedRowSetImpl();
            crs.setUrl(url);
            crs.setUsername(user);
            crs.setPassword(password);

            crs.setCommand("select * from liwy_user where id > ?");
            crs.setLong(1, 20);
            crs.setPageSize(2); // 分页
            crs.execute();

            do {
                while (crs.next()) {
                    String id = crs.getString(1);
                    String uname = crs.getString(2);
                    log.debug("NEXT:" + id + "/" + uname);
                }
            } while (crs.nextPage());

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
