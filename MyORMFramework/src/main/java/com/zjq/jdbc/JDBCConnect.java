package com.zjq.jdbc;

import com.zjq.entity.User;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过JDBC连接数据库
 * @author zjq
 * @date 2022/3/14
 */
public class JDBCConnect {

    /**
     * JDBC查询
     */
    @Test
    public void select() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 通过驱动管理类获取数据库链接
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/my-orm-framework?characterEncoding=utf-8", "root", "root");
            // 定义sql语句？表示占位符
            String sql = "select * from user where username = ?";
            // 获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数，第⼀个参数为sql语句中参数的序号(从1开始)，第⼆个参数为设置的参数值
            preparedStatement.setString(1, "zjq666888");
            // 向数据库发出sql执⾏查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            List<User> userList = new ArrayList<>(16);
            // 遍历查询结果集
            while (resultSet.next()) {
                User user = new User();
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String phone = resultSet.getString("phone");
                // 封装User
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setPhone(phone);
                userList.add(user);
            }
            System.out.println(userList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

    /**
     * JDBC新增
     */
    @Test
    public void insert() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 通过驱动管理类获取数据库链接
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/my-orm-framework?characterEncoding=utf-8", "root", "root");
            // 定义sql语句？表示占位符
            String sql = "insert into user(username,password,phone) values (?,?,?)";
            // 获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数，第⼀个参数为sql语句中参数的序号(从1开始)，第⼆个参数为设置的参数值
            preparedStatement.setString(1, "共饮一杯无");
            preparedStatement.setString(2, "888866669999");
            preparedStatement.setString(3, "17688886666");
            // 向数据库发出sql执⾏查询，查询出结果集
            int result = preparedStatement.executeUpdate();
            if(result>0){
                System.out.println("新增成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
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
