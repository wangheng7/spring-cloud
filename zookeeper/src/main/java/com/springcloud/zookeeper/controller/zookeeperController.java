package com.springcloud.zookeeper.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class zookeeperController {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value="/provide/zk")
    public String provideZk(){
        return "zookeeper"+port;
    }
}
