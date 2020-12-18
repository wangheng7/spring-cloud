package com.springcloud.hystrix.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:    Acontroller
 * Package:    com.springcloud.hystrix.controller
 * Description:
 * Datetime:    2020/12/8   20:44
 * Author:   XXXXX@XX.com
 */
@RestController
public class Aprovider {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/a")
    public String a(){
        return "a服务启动成功" + port;
    }

}
