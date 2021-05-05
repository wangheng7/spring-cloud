package com.springaop.service;

import java.lang.annotation.*;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogFilter {

    //操作类型
    String opera() default "";

    //业务模块
    String module() default "";

    String id() default "";


}
