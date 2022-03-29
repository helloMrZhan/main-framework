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

    /**
     * 测试二级缓存和sqlSession无关
     * @throws IOException
     */
    @Test
    public void testTwoCache() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //根据 sqlSessionFactory 产⽣ session
        SqlSession sqlSession1 = sessionFactory.openSession();
        SqlSession sqlSession2 = sessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper. class );
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper. class );
        //第⼀次查询，发出sql语句，并将查询的结果放⼊缓存中
        User u1 = userMapper1.selectUserByUserId(1);
        System.out.println(u1);
        sqlSession1.close(); //第⼀次查询完后关闭 sqlSession
        //第⼆次查询，即使sqlSession1已经关闭了，这次查询依然不发出sql语句
        User u2 = userMapper2.selectUserByUserId(1);
        System.out.println(u2);
        sqlSession2.close();
    }

    /**
     * 执⾏commit()操作，⼆级缓存数据清空
     * @throws IOException
     */
    @Test
    public void testTwoCache2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //根据 sqlSessionFactory 产⽣ session
        SqlSession sqlSession1 = sessionFactory.openSession();
        SqlSession sqlSession2 = sessionFactory.openSession();
        SqlSession sqlSession3 = sessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper. class );
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper. class );
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper. class );
        //第⼀次查询，发出sql语句，并将查询的结果放⼊缓存中
        User u1 = userMapper1.selectUserByUserId( 1 );
        System.out.println(u1);
        sqlSession1.close(); //第⼀次查询完后关闭sqlSession
        //执⾏更新操作，commit()
        u1.setUsername( "zjq" );
        userMapper3.updateById(u1);
        sqlSession3.commit();
        //第⼆次查询，由于上次更新操作，缓存数据已经清空(防⽌数据脏读)，这⾥必须再次发出sql语句
        User u2 = userMapper2.selectUserByUserId( 1 );
        System.out.println(u2);
        sqlSession2.close();
    }
}
