package com.zjq.producer;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq创建连接的工具类
 * @author zjq
 */
public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2. 设置参数
        //ip  默认值 localhost
        factory.setHost("localhost");
        //端口  默认值 5672
        factory.setPort(5672);
        //虚拟机 默认值/
        factory.setVirtualHost("/zjq");
        //用户名 默认 guest
        factory.setUsername("guest");
        //密码 默认值 guest
        factory.setPassword("guest");
        //3. 创建连接 Connection
        Connection connection = factory.newConnection();

        return connection;
    }

}
