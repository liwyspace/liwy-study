package com.liwy.study.jdbc.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * <b>名称：</b> 返回Connectin的代理对象的连接池实现-动态代理<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/1/16 16:12 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class LiwyConnPoolImpl2 implements LiwyConnPool {
    private ConnectionProvider provider = new ConnectionProvider();
    private final ArrayList<Connection> pool = new ArrayList<Connection>();
    private int poolSize = 5;

    public LiwyConnPoolImpl2() {
    }

    public LiwyConnPoolImpl2(int poolSize) {
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
        return getProxy(con, this);
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
            closeJdbcConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeJdbcConnection(Connection con) throws SQLException {
        ConnectionP conP = (ConnectionP) con;
        conP.getJdbcConnection().close();
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
                closeJdbcConnection(iter.next());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        pool.clear();
    }

    /** 返回动态连接代理 */
    private Connection getProxy(final Connection con, final LiwyConnPool pool) {
        InvocationHandler handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object args[])
                    throws Exception {
                if (method.getName().equals("close")) {
                    pool.releaseConnection((Connection) proxy);
                    return null;
                } else if (method.getName().equals("getJdbcConnection")) {
                    return con;
                } else {
                    return method.invoke(con, args);
                }
            }
        };

        return (Connection) Proxy.newProxyInstance(
                ConnectionP.class.getClassLoader(),
                new Class[] { ConnectionP.class }, handler);
    }

    interface ConnectionP extends Connection {
        Connection getJdbcConnection(); // 返回被代理的Connection对象
    }
}
