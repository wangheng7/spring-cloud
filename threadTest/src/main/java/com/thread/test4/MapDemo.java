package com.thread.test4;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName:    MapDemo
 * Package:    com.thread.test4
 * Description:
 */
public class MapDemo {
    public static void main(String[] args){
//        Map map = new HashMap();
        Map map = new ConcurrentHashMap();

        for(int x = 0;x < 30;x++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                    System.out.println(map);
                }
            },String.valueOf(x)).start();
        }
    }
}
