package com.zjq.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
/**
 * <p>日期类型转换器</p>
 *
 * @Author zjq
 * @Date 2021/8/2
 */
public class DateTypeHandler extends BaseTypeHandler<Date> {

    /**
     * 将java类型 转换成 数据库需要的类型
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        preparedStatement.setLong(i,time);
    }


    /**
     * 将数据库中类型 转换成java类型
     * @param resultSet 查询出的结果集
     * @param s 转换的字段名称
     * @return date
     * @throws SQLException sql异常
     */
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        //获得结果集中需要的数据(long) 转换成Date类型 返回
        long aLong = resultSet.getLong(s);
        Date date = new Date(aLong);
        return date;
    }

    /**
     * 将数据库中类型 转换成java类型
     */
    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        long aLong = resultSet.getLong(i);
        Date date = new Date(aLong);
        return date;
    }

    /**
     * 将数据库中类型 转换成java类型
     */
    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long aLong = callableStatement.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
}
