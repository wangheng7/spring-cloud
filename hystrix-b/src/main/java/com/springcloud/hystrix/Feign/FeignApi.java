package com.springcloud.hystrix.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:    FeignApi
 * Package:    com.springcloud.hystrix
 * Description:
 * Datetime:    2020/12/9   19:20
 * Author:   XXXXX@XX.com
 */
//@FeignClient(value="hystrix-c", fallback = FeignApiFallBack.class)
//public interface FeignApi {
//
//    /*
//     * 通过 hystrix-c 服务名调用 /c方法
//     * */
//    @RequestMapping("/c")
//    public String c();
//}
