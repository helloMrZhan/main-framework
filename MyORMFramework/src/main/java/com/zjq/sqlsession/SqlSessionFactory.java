package com.zjq.sqlsession;

/**
 * SqlSession工厂接口
 * @author zjq
 * @date 2022/3/15
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
