package com.zjq.test;

import com.zjq.dao.IUserDao;
import com.zjq.io.Resources;
import com.zjq.pojo.Configuration;
import com.zjq.pojo.User;
import com.zjq.sqlsession.SqlSession;
import com.zjq.sqlsession.SqlSessionFactory;
import com.zjq.sqlsession.SqlSessionFactoryBuilder;
import com.zjq.xmlbuilder.XMLConfigerBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 自定义持久层框架验证
 * @author Administrator
 */
public class MyORMFrameworkTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setPhone("15656455662");
        user.setUsername("zjq");
//        User user2 = sqlSession.selectOne("com.zjq.dao.IUserDao.findOne", user);
//
//        System.out.println(user2);

//        List<User> users = sqlSession.selectList("com.zjq.dao.IUserDao.findAll");
//        for (User user1 : users) {
//            System.out.println(user1);
//        }

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
    }


}
