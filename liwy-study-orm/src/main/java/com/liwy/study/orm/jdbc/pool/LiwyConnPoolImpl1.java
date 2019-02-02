package com.liwy.study.orm.jdbc.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * <b>名称：</b> 简单连接池实现<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/1/16 16:12 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class LiwyConnPoolImpl1 implements LiwyConnPool {
    private ConnectionProvider provider = new ConnectionProvider();
    private final ArrayList<Connection> pool = new ArrayList<Connection>();
    private int poolSize = 5;

    public LiwyConnPoolImpl1() {
    }

    public LiwyConnPoolImpl1(int poolSize) {
        this.poolSize = poolSize;
    }

    /** 从连接池中取出连接 */
    @Override
    public Connection getConnection() throws SQLException {
        synchronized (pool) {
            if (!pool.isEmpty()) {
                int last = pool.size() - 1;
                Connection con = pool.remove(last);
                return con;
            }
        }

        Connection con = provider.getConnection();
        return con;
    }

    /** 把连接返回连接池 */
    @Override
    public void releaseConnection(Connection con) throws SQLException {
        synchronized (pool) {
            int currentSize = pool.size();
            if (currentSize < poolSize) {
                pool.add(con);
                return;
            }
        }

        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() {
        close();
    }

    /** 关闭连接池 */
    @Override
    public void close() {
        Iterator<Connection> iter = pool.iterator();
        while (iter.hasNext()) {
            try {
                iter.next().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        pool.clear();
    }
}
