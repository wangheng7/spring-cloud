package com.springcloud.hystrix.controller;

import com.springcloud.hystrix.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:    PayController
 * Package:    com.springcloud.hystrix.controller
 * Description:
 */
@RestController
public class PayController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/timeout/{id}")
    public String paymentTimeout(@PathVariable("id")String id){
        String result = paymentService.paymentTimeout(id);
        return result;
    }
}
