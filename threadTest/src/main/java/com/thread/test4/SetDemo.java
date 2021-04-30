package com.thread.test4;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ClassName:    SetDemo
 * Package:    com.thread.test4
 * Description:
 * Datetime:    2021/4/6   14:27
 * Author:   XXXXX@XX.com
 */
public class SetDemo {
    public static void main(String[] args){
//        Set set = new HashSet();
        Set set = new CopyOnWriteArraySet();

        for(int x = 0;x < 30;x++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    set.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(set);
                }
            },String.valueOf(x)).start();

        }
    }
}
