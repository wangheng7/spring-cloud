package com.example.rabbitmqHaproxy;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static final String EXCHANGE_NAME = "";
    private static final String ROUTING_KEY = "";
    private static final String QUEUE_NAME = "test01";
    private static Connection conn;

    static{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.187.191");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");

        try {
            conn = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void run() {
        try {
            Channel channel = conn.createChannel();
//          channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            //声明一个队列
            channel.queueDeclare(QUEUE_NAME, false, true, true, null);
            String message = "Hello RabbitMQ";
            //发送消息到队列中
            for(int i=0;i<100;i++){
                channel.basicPublish("",QUEUE_NAME, null, message.getBytes("UTF-8"));
                try {
                    Thread.sleep(500);
                    System.out.println("Consumer: "+message+"---"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            channel.close();
            conn.close();
        } catch (IOException | TimeoutException e) {
            System.out.println("Exception Message: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
