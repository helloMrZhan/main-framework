package com.zjq.test;

import com.zjq.domain.User;
import com.zjq.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MapperTest {
    /**
     * 测试一级缓存
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //根据 sqlSessionFactory 产⽣ session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //模拟条件user
        User condition = new User();
        //condition.setId(1);
        condition.setUsername("zjq");
        //第⼀次查询，发出sql语句，并将查询出来的结果放进缓存中
        List<User> userList = mapper.findByCondition(condition);
        System.out.println("第一次查询结果：" + userList);
        //第⼆次查询，由于是同⼀个sqlSession,会在缓存中查询结果
        //如果有，则直接从缓存中取出来，不和数据库进⾏交互
        List<User> userList2 = mapper.findByCondition(condition);
        System.out.println("第二次查询结果：" + userList2);
        sqlSession.close();
    }

    /**
     * 测试一级缓存
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //根据 sqlSessionFactory 产⽣ session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //模拟条件user
        User condition = new User();
        //condition.setId(1);
        condition.setUsername("zjq");
        //第⼀次查询，发出sql语句，并将查询出来的结果放进缓存中
        List<User> userList = mapper.findByCondition(condition);
        System.out.println("第一次查询结果：" + userList);
        User user = new User();
        user.setId(3);
        user.setUsername("zjq666");
        user.setPassword("6566666666");
        //进⾏了⼀次更新操作，sqlSession.commit()
        mapper.updateById(user);
        sqlSession.commit();
        //第⼆次查询，由于是同⼀个sqlSession,会在缓存中查询结果
        //如果有，则直接从缓存中取出来，不和数据库进⾏交互
        List<User> userList2 = mapper.findByCondition(condition);
        System.out.println("第二次查询结果：" + userList2);
        sqlSession.close();
    }
}
