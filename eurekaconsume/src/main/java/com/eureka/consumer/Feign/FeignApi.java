package com.eureka.consumer.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通过 @FeignClient("provide-name") 注解来指定调用哪个服务
 * provide-name 就是提供者的 spring.application.name：应用名称
 * String hello();：可以发现，此方法就是提供者 HelloController 中的方法，这里要定义成接口
 * 注意要与提供者具有相同返回值，相同方法名以及相同参数
 */

@FeignClient(value="provide-name")
public interface FeignApi {

    /*
    * 通过 provide-name 服务名调用 /hello方法
    * */
    @RequestMapping("/hello")
    public String hello();

}
