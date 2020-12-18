package com.springcloud.hystrix.controller;

import com.springcloud.hystrix.feign.FeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:    Aconsumer
 * Package:    com.springcloud.hystrix.controller
 * Description:
 * Datetime:    2020/12/8   21:27
 * Author:   XXXXX@XX.com
 */
@RestController
public class Aconsumer {

    @Autowired(required = false)
    private FeignApi feignApi;

    /*
    * 通过 hystrix-b 服务名调用 b() 方法
    * */
    @RequestMapping("/aUseB")
    public String aUseB(){
        return feignApi.b();
    }

}
