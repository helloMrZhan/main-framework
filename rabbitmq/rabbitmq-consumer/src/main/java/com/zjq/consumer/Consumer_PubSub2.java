package com.zjq.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/**
 * 订阅模式消费者
 * @author zjq
 */
public class Consumer_PubSub2 {
    public static void main(String[] args) throws IOException, TimeoutException {

        //3. 创建连接 Connection
        Connection connection = ConnectionUtil.getConnection();
        //4. 创建Channel
        Channel channel = connection.createChannel();


        String queue1Name = "test_fanout_queue1";
        String queue2Name = "test_fanout_queue2";


        /*
        basicConsume(String queue, boolean autoAck, Consumer callback)
        参数：
            1. queue：队列名称
            2. autoAck：是否自动确认
            3. callback：回调对象

         */
        // 接收消息
        Consumer consumer = new DefaultConsumer(channel){
            /*
                回调方法，当收到消息后，会自动执行该方法

                1. consumerTag：标识
                2. envelope：获取一些信息，交换机，路由key...
                3. properties:配置信息
                4. body：数据

             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
              /*  System.out.println("consumerTag："+consumerTag);
                System.out.println("Exchange："+envelope.getExchange());
                System.out.println("RoutingKey："+envelope.getRoutingKey());
                System.out.println("properties："+properties);*/
                System.out.println("body："+new String(body));
                System.out.println("将日志信息保存数据库.....");
            }
        };
        channel.basicConsume(queue2Name,true,consumer);


        //关闭资源？不要

    }
}
