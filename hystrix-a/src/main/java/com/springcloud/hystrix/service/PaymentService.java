package com.springcloud.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * ClassName:    PaymentService
 * Package:    com.springcloud.hystrix.service
 * Description:
 */
@Service
public class PaymentService {

    public String paymentOk(String id){
        return "线程池："+Thread.currentThread().getName()+id;
    }

    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    public String paymentTimeout(String id){
//        int timeNum = 5;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+id+"超时";
    }

    public String paymentTimeoutHandler(String id){
        return "线程池："+Thread.currentThread().getName()+id+"请稍等";
    }

}
