package com.springaop.controller;

import com.springaop.entity.Student;
import com.springaop.service.StudentServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMethod {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentServiceImpl studentServiceImpl = context.getBean(StudentServiceImpl.class);

        Student stu = new Student();
        stu.setId("1");
        stu.setName("张三");
        stu.setAge("10");
        studentServiceImpl.addStudent(stu);

    }

}
