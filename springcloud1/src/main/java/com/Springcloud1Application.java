package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
@MapperScan("com.com.springcloud.hystrix.controller")
public class Springcloud1Application {

	public static void main(String[] args) {

		SpringApplication.run(Springcloud1Application.class, args);
	}
}
