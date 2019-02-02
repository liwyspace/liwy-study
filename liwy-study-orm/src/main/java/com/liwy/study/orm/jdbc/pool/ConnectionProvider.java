package com.liwy.study.orm.jdbc.pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/1/16 16:16 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class ConnectionProvider {

    private String JDBC_DRIVER;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;

    public ConnectionProvider() {
        JDBC_DRIVER = PropertyReader.get("dev_db_driver");
        DB_URL = PropertyReader.get("dev_db_url");
        DB_USER = PropertyReader.get("dev_db_username");
        DB_PASSWORD = PropertyReader.get("dev_db_password");
        try {
            Class jdbcDriver = Class.forName(JDBC_DRIVER);
            DriverManager.registerDriver((Driver) jdbcDriver
                    .newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection con = java.sql.DriverManager.getConnection(DB_URL, DB_USER,
                DB_PASSWORD);
        return con;
    }
}
