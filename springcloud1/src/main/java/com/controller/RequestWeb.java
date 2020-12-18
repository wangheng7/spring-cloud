package com.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * ClassName:    RequestWeb
 * Package:    com.example.com.springcloud.hystrix.controller
 * Description:
 * Datetime:    2020/9/13   15:12
 * Author:   XXXXX@XX.com
 */
@RestController
public class RequestWeb {

    @Autowired
    private DiscoveryClient client;

    // 服务注册
    @Qualifier("eurekaRegistration")
    @Autowired
    private org.springframework.cloud.client.serviceregistry.Registration eurekaRegistration;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        System.out.println(eurekaRegistration.getHost()+"------"+eurekaRegistration.getServiceId());
        return "login success";
    }
}
