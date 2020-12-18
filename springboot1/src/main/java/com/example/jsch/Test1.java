package com.example.jsch;

import com.example.domain.UserDo;

import java.lang.reflect.Field;

/**
 * ClassName:    Test1
 * Package:    com.example.jsch
 * Description:
 * Datetime:    2020/8/4   18:29
 * Author:   XXXXX@XX.com
 */
public class Test1 {
    public static void main(String [] args) {
        Field[] fields = UserDo.class.getDeclaredFields();
        for(Field f:fields) {
            System.out.println("---------------------------属性" + f);

        }
    }
}
