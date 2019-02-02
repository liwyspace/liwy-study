package com.liwy.study.jdbc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

/**
 * <b>名称：</b> 元数据测试类<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/12/4 16:51 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class JdbcMetaTest {
    private static final Logger log = LoggerFactory.getLogger(JdbcMetaTest.class);

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
     * 元数据
     *
     * @throws SQLException
     */
    @Test
    public void testMetaData() throws SQLException {
        Connection con = this.getConn();
        DatabaseMetaData dmd = con.getMetaData();
        System.out.println("======获取数据库信息======");
        System.out.println("数据库主版本号：" + dmd.getDatabaseMajorVersion());
        System.out.println("数据库附版本号：" + dmd.getDatabaseMinorVersion());
        System.out.println("数据库产品名称：" + dmd.getDatabaseProductName());
        System.out.println("数据库产品版本号：" + dmd.getDatabaseProductVersion());
        System.out.println("驱动主版本号：" + dmd.getDriverMajorVersion());
        System.out.println("驱动附版本号：" + dmd.getDriverMinorVersion());
        System.out.println("驱动名称：" + dmd.getDriverName());
        System.out.println("驱动版本号：" + dmd.getDriverVersion());
        System.out.println("JDBC主版本号：" + dmd.getJDBCMajorVersion());
        System.out.println("JDBC附版本号：" + dmd.getJDBCMinorVersion());
        System.out.println("URL：" + dmd.getURL());
        System.out.println("用户名：" + dmd.getUserName());
        System.out.println("是否只读数据库：" + dmd.isReadOnly());

        System.out.println("======遍历指定数据库下的所有表与视图======");
        ResultSet tables = dmd.getTables(con.getCatalog(), null, "%",
                new String[] { "TABLE", "VIEW" });
        showResultSet(tables);

        System.out.println("======遍历指定数据库下指定表的所列======");
        ResultSet columns = dmd.getColumns(con.getCatalog(), null, "liwy_user",
                "%");
        showResultSet(columns);

    }

    /**
     * 通过MetaData遍历结果集
     *
     * @param rs
     */
    private void showResultSet(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                if (i > 1)
                    System.out.print(",");
                System.out.print(metaData.getColumnLabel(i));
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    if (i > 1)
                        System.out.print(",");
                    int type = metaData.getColumnType(i);

                    switch (type) {
                        case Types.INTEGER:
                            System.out.print(rs.getInt(i));
                            break;
                        case Types.BIGINT:
                            System.out.print(rs.getLong(i));
                            break;
                        case Types.FLOAT:
                            System.out.print(rs.getFloat(i));
                            break;
                        case Types.LONGVARBINARY:
                            System.out.print(rs.getBlob(i).length());
                            break;
                        case Types.LONGVARCHAR:
                            System.out.print(rs.getClob(i).getSubString(1, 5));
                            break;
                        case Types.CHAR:
                        case Types.VARCHAR:
                            System.out.print(rs.getString(i));
                            break;
                        default:
                            System.out.print(type);
                    }
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
