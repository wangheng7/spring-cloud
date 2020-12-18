package com.example.test;

import com.example.service.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivemqTest {

    @Autowired
    private Producer producer;

    @Test
    public void contextLoads(){
        Destination destination = new ActiveMQQueue("queueTest1");

        producer.sendMessage(destination,"wh");
    }
}
