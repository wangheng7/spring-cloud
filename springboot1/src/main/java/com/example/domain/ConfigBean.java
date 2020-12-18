package com.example.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//test1是test.propertiex里的name
//@ConfigurationProperties(prefix="test1")
//@Component
@Configuration
@PropertySource(value="classpath:test.yml")
@ConfigurationProperties(prefix="com.example")
public class ConfigBean {
    private String name;

    private String age;

    private String uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
