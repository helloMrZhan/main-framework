package com.zjq.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjq.domain.User;
import com.zjq.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MybatisTest {


    /**
     * 一次性批量插入
     * 报错：Cause: com.mysql.jdbc.PacketTooBigException: Packet for query is too large (27759038 >yun 4194304).
     * You can change this value on the server by setting the max_allowed_packet' variable.
     * @throws IOException
     */
    @Test
    public void testBatchInsertUserFail() throws IOException {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession session = sqlSessionFactory.openSession();
        System.out.println("===== 开始插入数据 =====");
        long startTime = System.currentTimeMillis();
        try {
            List<User> userList = new ArrayList<>();
            for (int i = 1; i <= 300000; i++) {
                User user = new User();
                user.setId(i);
                user.setUsername("共饮一杯无 " + i);
                user.setAge((int) (Math.random() * 100));
                userList.add(user);
//                if (i % 1000 == 0) {
//                    session.insert("insertBatch", userList);
//                    session.commit(); // 每 1000 条数据提交一次事务
//                    userList.clear();
//                }
            }
            session.insert("batchInsertUser", userList); // 最后插入剩余的数据
            session.commit();

            long spendTime = System.currentTimeMillis()-startTime;
            System.out.println("成功插入 30 万条数据,耗时："+spendTime+"毫秒");
        } finally {
            session.close();
        }
    }


    /**
     * 循环一条一条插入
     * PS:耗时太久，黄花菜都凉了
     * @throws IOException
     */
    @Test
    public void testCirculateInsertUser() throws IOException {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession session = sqlSessionFactory.openSession();
        System.out.println("===== 开始插入数据 =====");
        long startTime = System.currentTimeMillis();
        try {
            for (int i = 1; i <= 300000; i++) {
                User user = new User();
                user.setId(i);
                user.setUsername("共饮一杯无 " + i);
                user.setAge((int) (Math.random() * 100));
                // 一条一条新增
                session.insert("insertUser", user);
                session.commit();
            }

            long spendTime = System.currentTimeMillis()-startTime;
            System.out.println("成功插入 30 万条数据,耗时："+spendTime+"毫秒");
        } finally {
            session.close();
        }
    }


    /**
     * 分批次批量插入
     * @throws IOException
     */
    @Test
    public void testBatchInsertUser() throws IOException {
        InputStream resourceAsStream =
                Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession session = sqlSessionFactory.openSession();
        System.out.println("===== 开始插入数据 =====");
        long startTime = System.currentTimeMillis();
        int waitTime = 10;
        try {
            List<User> userList = new ArrayList<>();
            for (int i = 1; i <= 300000; i++) {
                User user = new User();
                user.setId(i);
                user.setUsername("共饮一杯无 " + i);
                user.setAge((int) (Math.random() * 100));
                userList.add(user);
                if (i % 1000 == 0) {
                    session.insert("batchInsertUser", userList);
                    // 每 1000 条数据提交一次事务
                    session.commit();
                    userList.clear();
                }
            }
            // 最后插入剩余的数据
            if(!CollectionUtils.isEmpty(userList)) {
                session.insert("batchInsertUser", userList);
                session.commit();
            }

            long spendTime = System.currentTimeMillis()-startTime;
            System.out.println("成功插入 30 万条数据,耗时："+spendTime+"毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
