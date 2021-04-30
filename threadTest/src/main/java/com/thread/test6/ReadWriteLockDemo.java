package com.thread.test6;

/**
 * ClassName:    ReadWriteLockDemo
 * Package:    com.thread.test6
 * Description:
 */
public class ReadWriteLockDemo {

    public static void main(String[] args){
        MyCache cache = new MyCache();

        for(int x=1;x<6;x++){
            final int temp = x;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    cache.put(String.valueOf(temp),String.valueOf(temp));
                }
            },String.valueOf(x)).start();
        }

        for(int x=1;x<6;x++){
            final int temp = x;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    cache.get(String.valueOf(temp));
                }
            },String.valueOf(x)).start();
        }
    }
}
