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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
                if (i % 5000 == 0) {
                    session.insert("batchInsertUser", userList);
                    // 每 1000 条数据提交一次事务
                    session.commit();
                    userList.clear();
                    System.out.println("成功插入第 "+ i+" 条数据");
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

    /**
     * JDBC循环插入
     * @throws IOException
     */
    @Test
    public void testJDBCCirculateInsertUser() throws IOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String databaseURL = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";

        try {
            connection = DriverManager.getConnection(databaseURL, user, password);
            System.out.println("===== 开始插入数据 =====");
            long startTime = System.currentTimeMillis();
            String sqlInsert = "INSERT INTO t_user ( username, age) VALUES ( ?, ?)";
            preparedStatement = connection.prepareStatement(sqlInsert);

            Random random = new Random();

            for (int i = 1; i <= 300000; i++) {
                preparedStatement.setString(1, "共饮一杯无 " + i);
                preparedStatement.setInt(2, random.nextInt(100));

                preparedStatement.executeUpdate();
                System.out.println("成功插入第 "+ i+" 条数据");
            }
            long spendTime = System.currentTimeMillis()-startTime;
            System.out.println("成功插入 30 万条数据,耗时："+spendTime+"毫秒");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * JDBC分批次批量插入
     * @throws IOException
     */
    @Test
    public void testJDBCBatchInsertUser() throws IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String databaseURL = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";

        try {
            connection = DriverManager.getConnection(databaseURL, user, password);
            // 关闭自动提交事务，改为手动提交
            connection.setAutoCommit(false);
            System.out.println("===== 开始插入数据 =====");
            long startTime = System.currentTimeMillis();
            String sqlInsert = "INSERT INTO t_user ( username, age) VALUES ( ?, ?)";
            preparedStatement = connection.prepareStatement(sqlInsert);

            Random random = new Random();
            for (int i = 1; i <= 300000; i++) {
                preparedStatement.setString(1, "共饮一杯无 " + i);
                preparedStatement.setInt(2, random.nextInt(100));
                // 添加到批处理中
                preparedStatement.addBatch();

                if (i % 1000 == 0) {
                    // 每1000条数据提交一次
                    preparedStatement.executeBatch();
                    connection.commit();
                    System.out.println("成功插入第 "+ i+" 条数据");
                }

            }
            // 处理剩余的数据
            preparedStatement.executeBatch();
            connection.commit();
            long spendTime = System.currentTimeMillis()-startTime;
            System.out.println("成功插入 30 万条数据,耗时："+spendTime+"毫秒");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
