package com.example.test;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail testQuartz1(){
        return JobBuilder.newJob(JobTest1.class).withIdentity("JobTest1").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger1(){
        //使用的固定间隔
        SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(testQuartz1())
                .withIdentity("JobTest1")
                .withSchedule(ssb)
                .build();
    }

    @Bean
    public JobDetail testQuartz2(){
        return JobBuilder.newJob(JobTest2.class).withIdentity("JobTest2").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger2(){
        //cron表达式
        return TriggerBuilder.newTrigger().forJob(testQuartz2())
                .withIdentity("JobTest2")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                .build();
    }
}
