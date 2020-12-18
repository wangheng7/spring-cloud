package com.eureka.provide.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:    HelloController
 * Package:    com.springcloud.hystrix.controller
 * Description:
 * Datetime:    2020/12/3   17:37
 * Author:   XXXXX@XX.com
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Spring Cloud！！！port：" + port;
    }

}
