package com.zjq.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjq.domain.User;
import com.zjq.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PluginTest {


    @Test
    public void testCustomPlugin() throws IOException {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User condition = new User();
        //condition.setId(1);
        condition.setUsername("zjq");
        List<User> byPaging = userMapper.findByCondition(condition);
        for (User user : byPaging) {
            System.out.println(user);
        }
    }

    @Test
    public void testPageHelper() throws IOException {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //设置分⻚参数
        Page pageInfo = PageHelper.startPage(1, 2);
        User condition = new User();
        //condition.setId(1);
        //condition.setUsername("zjq");
        List<User> select = userMapper.findByCondition(condition);
        for (User user : select) {
            System.out.println(user);
        }
        //其他分⻚的数据
        //PageInfo<User> pageInfo = new PageInfo<User>(select);
        System.out.println("总条数："+pageInfo.getTotal());
        System.out.println("总⻚数："+pageInfo. getPages ());
        System.out.println("当前⻚："+pageInfo. getPageNum());
        System.out.println("每⻚显示⻓度："+pageInfo.getPageSize());

    }


}
