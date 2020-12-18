package com.example.rabbitmqHaproxy;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    private static final String EXCHANGE_NAME = "";
    private static final String ROUTING_KEY = "";
    private static final String QUEUE_NAME = "";
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

    public void run() {
        try {
            Channel channel = conn.createChannel();
//          channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            channel.queueDeclare(QUEUE_NAME, false, true, true, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, false, consumer);

            while(true){
                try {
                    QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                    String msg = new String(delivery.getBody());
                    System.out.println("msg = "+msg);

                    Long tag = delivery.getEnvelope().getDeliveryTag();
                    channel.basicAck(tag, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
