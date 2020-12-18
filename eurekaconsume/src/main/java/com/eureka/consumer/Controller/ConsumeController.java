package com.eureka.consumer.Controller;

import com.eureka.consumer.Feign.FeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:    ConsumeController
 * Package:    com.springcloud.hystrix.controller.Feign.Controller
 * Description:
 * Datetime:    2020/12/4   10:19
 * Author:   XXXXX@XX.com
 */
@RestController
public class ConsumeController {

    @Autowired(required = false)
    private FeignApi feignApi;

    @RequestMapping("/feign")
    public String feign(){
        return feignApi.hello();
    }

}
