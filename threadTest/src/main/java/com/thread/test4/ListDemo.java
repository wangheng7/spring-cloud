package com.thread.test4;

import java.util.*;

public class ListDemo {
    public static void main(String[] args){
//        List list = new ArrayList();
//        List list = new Vector();
        List list = Collections.synchronizedList(new ArrayList());

        for(int x = 0;x < 3;x++){
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
