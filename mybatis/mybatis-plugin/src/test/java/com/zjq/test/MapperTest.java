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
import java.util.List;

public class MapperTest {

    @Test
    public void test() throws IOException {
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

}
