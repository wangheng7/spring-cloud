package com.test.controller;

import com.test.domain.ServiceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
public class ServiceConfiguration {
//    @Bean
//    public Service service(ServiceProperties serviceProperties){
//
//        return new Service(serviceProperties.getMessage());
//    }
}
