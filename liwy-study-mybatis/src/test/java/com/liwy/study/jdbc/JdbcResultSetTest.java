package com.liwy.study.jdbc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/12/4 16:52 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class JdbcResultSetTest {

    private static final Logger log = LoggerFactory.getLogger(JdbcQueryTest.class);

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
     * <b>描述：</b> 可滚动、可更新的结果集<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * <p>并非所有的查询都会返回可更新的结果集。如果查询设计多个表格的连接操作，那么它所产生的结果集将使不可更新的。
     * 如果查询只涉及一个表格，或者在查询时是使用主键连接多个表格的，那么它所产生的结果集将是可更新的结果集</p>
     *
     * <p>在结果集打开期间，必须始终与数据库保持连接</p>
     *
     * @param
     * @return void
     */
    @Test
    public void testScroll() {
        try {
            Connection con = this.getConn();

            // 判断数据库是否支持滚动与更新
            log.debug("TYPE_FORWARD_ONLY:"
                    + con.getMetaData().supportsResultSetType(
                    ResultSet.TYPE_FORWARD_ONLY));
            log.debug("TYPE_SCROLL_INSENSITIVE:"
                    + con.getMetaData().supportsResultSetType(
                    ResultSet.TYPE_SCROLL_INSENSITIVE));
            log.debug("TYPE_SCROLL_SENSITIVE:"
                    + con.getMetaData().supportsResultSetType(
                    ResultSet.TYPE_SCROLL_SENSITIVE));
            log.debug("CONCUR_READ_ONLY:"
                    + con.getMetaData().supportsResultSetConcurrency(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY));
            log.debug("CONCUR_UPDATABLE:"
                    + con.getMetaData().supportsResultSetConcurrency(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE));

            String sql = "select * from liwy_user";
            PreparedStatement pst = con.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                Byte sex = rs.getByte("sex");
                String email = rs.getString("email");
                log.debug("id=" + id + ",name=" + name + ",sex="
                        + sex + ",email=" + email);
            }

            // 向前移动
            while (rs.previous()) {
                String id = rs.getString(1);
                String uname = rs.getString(2);
                log.debug("PRE" + id + "/" + uname);
            }

            // 相对移动
            while (rs.relative(2)) {
                String id = rs.getString(1);
                String uname = rs.getString(2);
                log.debug("REL2:" + id + "/" + uname);
            }
            while (rs.relative(-3)) {
                String id = rs.getString(1);
                String uname = rs.getString(2);
                log.debug("REL-3:" + id + "/" + uname);
            }

            // 绝对移动
            rs.absolute(5);
            log.debug("第五行:" + rs.getRow());
            rs.absolute(-2);
            log.debug("倒数第二行:" + rs.getRow());
            rs.absolute(99);
            log.debug("超出范围:" + rs.getRow());

            // 更新结果集
            rs.absolute(5);
            log.debug("更新行：" + rs.getRow());
            rs.updateString(2, "天使111");
            rs.updateRow();

            // 新增行
            rs.moveToInsertRow();
            log.debug("新增行：" + rs.getRow());
            rs.updateString(2, "新增用户111");
            rs.updateByte(3, (byte) 1);
            rs.insertRow();
            rs.moveToCurrentRow();

            // 删除行
            rs.absolute(8);
            log.debug("删除行：" + rs.getRow());
            rs.deleteRow();

            rs.close();
            pst.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
