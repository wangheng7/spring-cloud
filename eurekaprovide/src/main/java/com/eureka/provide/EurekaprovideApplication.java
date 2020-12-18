package com.eureka.provide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient  // 在注册中心发现服务
public class EurekaprovideApplication {

    public static void main(String[] args){
        SpringApplication.run(EurekaprovideApplication.class,args);
    }
}
