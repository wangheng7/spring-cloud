package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service("producer")
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    //发送消息，destination是发送的队列，message是待发送的消息
    public void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }

//    @JmsListener(destination = "out.queue")
//    public void consumerMessage(String text){
//        System.out.println("从out.queue队列收到的回复:"+text);
//    }
}
