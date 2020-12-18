package com.example.test;

import com.example.domain.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({ConfigBean.class})
public class Test1 {

    @Autowired
    private ConfigBean configBean;

    @RequestMapping("/")
    public String method(){
        return "hello world";
    }

    @RequestMapping(value="/springboottest")
    public String method2(){
        return configBean.getName()+"+"+configBean.getUuid();
    }

}
