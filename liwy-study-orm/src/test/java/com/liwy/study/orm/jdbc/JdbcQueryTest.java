package com.liwy.study.orm.jdbc;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

/**
 * <b>名称：</b> 查询操作<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/12/4 16:52 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class JdbcQueryTest {
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
     * <b>描述：</b> 测试预处理语句<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testPrepareStatement() throws SQLException {
        Connection conn = this.getConn();
        try {
            String sql = "SELECT * FROM liwy_user WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, 3L);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                Byte sex = resultSet.getByte("sex");
                String email = resultSet.getString("email");
                System.out.println("id=" + id + ",name=" + name + ",sex="
                        + sex + ",email=" + email);
            }
            resultSet.close();
            preparedStatement.close();
        } finally {
            conn.close();
        }
    }

    /**
     * <b>描述：</b> 测试LOB类型数据<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return void
     */
    @Test
    public void testLob() throws SQLException {
        Connection conn = this.getConn();
        try {
            PreparedStatement pstat = conn.prepareStatement("INSERT INTO liwy_user(username, sex, email, icon, content) VALUES(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            pstat.setString(1, "头像");
            pstat.setInt(2, 1);
            pstat.setString(3, "touxiang@163.com");

            // 设置BLOB
            FileInputStream fin = new FileInputStream(JdbcQueryTest.class.getResource("/").getPath() + "jdbc/image.gif");
            pstat.setBinaryStream(4, fin, fin.available());

            // 设置CLOB
            FileInputStream fin2 = new FileInputStream(JdbcQueryTest.class.getResource("/").getPath() + "jdbc/content.txt");
            InputStreamReader reader = new InputStreamReader(fin2);
            pstat.setCharacterStream(5, reader, fin2.available());

            // 提交数据
            pstat.executeUpdate();

            Long primerykey = 0L;
            ResultSet resultSet = pstat.getGeneratedKeys();
            if (resultSet.next()) {
                primerykey = resultSet.getLong(1);
            }

            fin.close();
            fin2.close();
            reader.close();
            pstat.close();

            PreparedStatement stmt = conn.prepareStatement("SELECT id,username,sex, email, icon, content FROM liwy_user WHERE id=?");
            stmt.setLong(1, primerykey);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // 读取BLOB
                Blob blob = rs.getBlob(5);
                InputStream in = blob.getBinaryStream();
                FileOutputStream fout = new FileOutputStream("E:/test_bak.gif");
                int b = -1;
                while ((b = in.read()) != -1)
                    fout.write(b);
                fout.close();
                in.close();

                // 读取CLOB
                Clob clob = rs.getClob(6);
                System.out.println(clob.getSubString(1, (int) clob.length()));
                Reader reader2 = clob.getCharacterStream();
                FileWriter writer = new FileWriter("E:/test_bak.txt");
                int c = -1;
                while ((c = reader2.read()) != -1) {
                    writer.write(c);
                }
                writer.close();
                reader2.close();

            }
            rs.close();
            stmt.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    /**
     * <b>描述：</b> 测试多结果集<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     * <p>
     * <pre>
     *     DELIMITER //
     *     DROP PROCEDURE IF EXISTS procQueryChannelTag//
     *     CREATE PROCEDURE procQueryChannelTag()
     *     BEGIN
     *     SELECT * FROM liwy_channel;
     *     SELECT * FROM liwy_tag;
     *     END//
     * </pre>
     *
     * @param
     * @return void
     */
    @Test
    public void testMoreResults() throws SQLException {
        Connection conn = this.getConn();
        try {
            CallableStatement stat = conn.prepareCall("{call procQueryChannelTag()}");
            boolean isResult = stat.execute();
            boolean done = false;
            while (!done) {
                if (isResult) {
                    ResultSet result = stat.getResultSet();
                    showResultSet(result);
                } else {
                    int updataCount = stat.getUpdateCount();
                    if (updataCount >= 0) {
                        System.out.println("更新了" + updataCount + "条数据！");
                    } else {
                        done = true;
                    }

                }
                isResult = stat.getMoreResults();
            }

            stat.close();

        } finally {
            conn.close();
        }
    }

    /** 通过MetaData遍历结果集 */
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
