package com.example.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    //用JmsListener配置消费者监听的队列，参数text是接收的消息
    @JmsListener(destination = "queueTest1")
    public void receiveQueue(String text){
        System.out.println("Consumer收到的报文:"+text);
    }
}
