package com.liwy.study.jdbc.pool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <b>名称：</b> 我的连接池接口<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/1/16 16:09 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public interface LiwyConnPool {
    /** 从连接池中取出连接，如果连接池缓存不为空，就从中取出一个连接并将其返回，否则新建一个连接并将其返回 */
    Connection getConnection() throws SQLException;

    /** 把连接放回连接池，如果连接池缓存未满，就把连接放回连接池缓存，否则就关闭该连接 */
    void releaseConnection(Connection con) throws SQLException;

    /** 关闭连接池，把连接池缓存中的所有连接关闭，再清空连接池缓存 */
    void close();
}
