package com.zjq.sqlsession;

import com.zjq.pojo.Configuration;
import com.zjq.xmlbuilder.XMLConfigerBuilder;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 创建SqlSessionFactory的SqlSessionFactoryBuilder
 *
 * @author zjq
 * @date 2022/3/15
 */
public class SqlSessionFactoryBuilder {

    private Configuration configuration;

    public SqlSessionFactoryBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSessionFactory build(InputStream inputStream) throws
            DocumentException, PropertyVetoException, ClassNotFoundException, IOException {
        //1.dom4j解析配置⽂件，封装Configuration
        XMLConfigerBuilder xmlConfigerBuilder = new
                XMLConfigerBuilder(configuration);
        Configuration configuration =
                xmlConfigerBuilder.parseConfiguration(inputStream);
        //2.创建SqlSessionFactory对象（工厂类），生产sqlSession:会话对象
        SqlSessionFactory sqlSessionFactory = new
                DefaultSqlSessionFactory(configuration);
        return sqlSessionFactory;
    }
}
