package com.zjq.test;

import com.zjq.domain.Order;
import com.zjq.domain.User;
import com.zjq.mapper.OrderMapper;
import com.zjq.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest3 {

    private UserMapper mapper;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(UserMapper.class);
    }


    @Test
    public void testUserAndOrderAll(){
        List<User> userAndOrderAll = mapper.findUserAndOrderAll();
        for (User user : userAndOrderAll) {
            System.out.println(user);
        }
    }



}
