package com.springcloud.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//zookeeper作为注册中心时注册服务
@EnableDiscoveryClient
public class ZookeeperApplication {
    public static void main(String[] args){
        SpringApplication.run(ZookeeperApplication.class,args);
    }
}
