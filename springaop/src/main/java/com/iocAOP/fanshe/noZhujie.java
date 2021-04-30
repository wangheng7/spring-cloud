package com.iocAOP.fanshe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassName:    noZhujie
 * Package:    com.iocAOP.fanshe
 * Description:
 * Datetime:    2021/4/27   20:46
 * Author:   XXXXX@XX.com
 */
public class noZhujie {

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        UserService userService = new UserService();
        System.out.println(userService);

        UserController userController = new UserController();
        //完成注入的功能
        Class<? extends UserController> clazz = userController.getClass();
        //获取属性
        Field shuXing = clazz.getDeclaredField("userService");
        //设置访问属性
        shuXing.setAccessible(true);
        //获取对应的set方法
        String name = shuXing.getName();
        name = name.substring(0,1).toUpperCase() + name.substring(1,name.length());

        String methodName = "set"+name;
        Method fangFa = clazz.getMethod(methodName,UserService.class);
        //调用setUserService的方法
        fangFa.invoke(userController,userService);
        System.out.println(userController.getUserService());
    }
}
