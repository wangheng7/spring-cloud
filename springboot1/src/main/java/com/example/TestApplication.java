package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableScheduling
@MapperScan({"com.example.dao","com.example.com.springcloud.hystrix.controller"})
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@RequestMapping("/")
	public String home(){
		return "hello docker";
	}
}
