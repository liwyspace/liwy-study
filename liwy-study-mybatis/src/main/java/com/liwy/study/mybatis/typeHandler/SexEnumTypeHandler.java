package com.liwy.study.mybatis.typeHandler;

import com.liwy.commons.lang.EnumUtils;
import com.liwy.study.mybatis.bo.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * <b>名称：</b> 自定义的类型转换器<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/27 15:47 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@MappedJdbcTypes(JdbcType.TINYINT)
@MappedTypes(SexEnum.class)
public class SexEnumTypeHandler extends BaseTypeHandler<SexEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setByte(i, sexEnum.getKey());
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return SexEnum.getValueByKey(resultSet.getByte(s));
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return  SexEnum.getValueByKey(resultSet.getByte(i));
    }

    @Override
    public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return SexEnum.getValueByKey(callableStatement.getByte(i));
    }
}
