package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName:    ConsumerController
 * Package:    com.example.com.springcloud.hystrix.controller
 * Description:
 */
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value="/ribbonconsumer",method = RequestMethod.GET)
    public String consumerTest(){
        return restTemplate.getForEntity("http://eureka-test/login",String.class).getBody();
    }

}
