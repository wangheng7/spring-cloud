package com.thread.test4;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListDemo {
    public static void main(String[] args){
//        List list = new ArrayList();
//        List list = new Vector();

        //转换成线程安全的
//        List list = Collections.synchronizedList(new ArrayList());

        //先复制一份，在复制的集合中加锁，并写入数据，再释放锁，新的集合替代老的集合
        List list = new CopyOnWriteArrayList();

        for(int x = 0;x < 30;x++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(list);
                }
            },String.valueOf(x)).start();
        }
    }
}
