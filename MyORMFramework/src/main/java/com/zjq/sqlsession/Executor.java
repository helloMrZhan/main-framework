package com.zjq.sqlsession;

import com.zjq.pojo.Configuration;
import com.zjq.pojo.MappedStatement;

import java.util.List;

/**
 * SQL执行器接口
 *
 * @author zjq
 * @date 2022/3/15
 */
public interface Executor {
    /**
     * 执行查询SQL
     * @param configuration
     * @param mappedStatement
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}