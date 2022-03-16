package com.zjq.sqlsession;

import com.zjq.pojo.Configuration;

/**
 * SqlSession工厂实现类
 *
 * @author zjq
 * @date 2022/3/15
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}