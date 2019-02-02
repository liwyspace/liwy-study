package com.liwy.study.orm.mybatis.plugins;

import com.liwy.study.orm.mybatis.bo.Pageable;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/12/3 17:48 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class PagePlugin implements Interceptor {
    private Logger logger = LoggerFactory.getLogger(PagePlugin.class);

    //    默认页码
    private Integer defaultPage;
    //    默认每页显示条数
    private Integer defaultPageSize;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("=============分页插件=============");
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        while(metaStatementHandler.hasGetter("h")){
            Object o = metaStatementHandler.getValue("h");
            metaStatementHandler = SystemMetaObject.forObject(o);
        }
        while (metaStatementHandler.hasGetter("target")){
            Object o = metaStatementHandler.getValue("target");
            metaStatementHandler = SystemMetaObject.forObject(o);
        }

        String sql = statementHandler.getBoundSql().getSql();
//        检测未通过，不是select语句
        if (!checkIsSelectFalg(sql)) {
            return invocation.proceed();
        }

        BoundSql boundSql = statementHandler.getBoundSql();

        Object paramObject = boundSql.getParameterObject();

        Pageable pageParam = getPageParam(paramObject);

        if (pageParam == null) {
            return invocation.proceed();
        }

        Integer pageNum = pageParam.getOffset() == null ? defaultPage : pageParam.getOffset();
        Integer pageSize = pageParam.getPageSize() == null ? defaultPageSize : pageParam.getPageSize();

        //修改sql
        return updateSql2Limit(invocation, metaStatementHandler, boundSql, pageNum, pageSize);
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String strDefaultPage = properties.getProperty("page", "0");
        String strDefaultPageSize = properties.getProperty("pageSize", "20");
        defaultPage = Integer.valueOf(strDefaultPage);
        defaultPageSize = Integer.valueOf(strDefaultPageSize);
    }

    /**
     * <b>描述：</b> 判断是否是select语句，只有select语句，才会用到分页<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param sql
     * @return boolean
     */
    private boolean checkIsSelectFalg(String sql) {
        String trimSql = sql.trim();
        int index = trimSql.toLowerCase().indexOf("select");
        return index == 0;
    }

    /*
    获取分页的参数

    参数可以通过map，@param注解进行参数传递。或者请求pojo继承自PageParam  将PageParam中的分页数据放进去
     */
    /**
     * <b>描述：</b> <br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param paramerObject
     * @return Pageable
     */
    private Pageable getPageParam(Object paramerObject) {
        if (paramerObject == null) {
            return null;
        }

        Pageable pageParam = null;
        //通过map和@param注解将PageParam参数传递进来，pojo继承自PageParam不推荐使用  这里从参数中提取出传递进来的pojo继承自PageParam

//        首先处理传递进来的是map对象和通过注解方式传值的情况，从中提取出PageParam,循环获取map中的键值对，取出PageParam对象
        if (paramerObject instanceof Map) {
            Map<String, Object> params = (Map<String, Object>) paramerObject;
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() instanceof Pageable) {
                    return (Pageable) entry.getValue();
                }
            }
        } else if (paramerObject instanceof Pageable) {
//            继承方式 pojo继承自PageParam 只取出我们希望得到的分页参数
            pageParam = (Pageable) paramerObject;

        }
        return pageParam;
    }

    //    修改原始sql语句为分页sql语句
    private Object updateSql2Limit(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, int page, int pageSize) throws InvocationTargetException, IllegalAccessException, SQLException {
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        //构建新的分页sql语句
        String limitSql = "select * from (" + sql + ") $_paging_table limit ?,?";
        //修改当前要执行的sql语句
        metaStatementHandler.setValue("delegate.boundSql.sql", limitSql);
        //相当于调用prepare方法，预编译sql并且加入参数，但是少了分页的两个参数，它返回一个PreparedStatement对象
        PreparedStatement ps = (PreparedStatement) invocation.proceed();
        //获取sql总的参数总数
        int count = ps.getParameterMetaData().getParameterCount();
        //设置与分页相关的两个参数
        ps.setInt(count - 1, (page - 1) * pageSize);
        ps.setInt(count, pageSize);
        return ps;
    }
}
