package com.springcloud.hystrix.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:    Bprovider
 * Package:    com.springcloud.hystrix.controller
 * Description:
 * Datetime:    2020/12/9   19:55
 * Author:   XXXXX@XX.com
 */
@RestController
public class Bprovider {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/b")
    public String b(){
        return "b服务启动成功" + port;
    }

}
