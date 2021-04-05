package com.thread.test3;

/**
 * ClassName:    WaitNotifyDemo
 * Package:    com.thread.test3
 * Description:
 * Datetime:    2021/4/1   21:53
 * Author:   XXXXX@XX.com
 */
public class WaitNotifyDemo {
    public static void main(String[] args){
//        Add1Del1 ad = new Add1Del1();
        AddDelLock ad = new AddDelLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=1;x<11;x++){
                    try {
                        Thread.sleep(600);
                        ad.add();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"A线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=1;x<11;x++){
                    try {
                        Thread.sleep(700);
                        ad.del();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"B线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=1;x<11;x++){
                    try {
                        Thread.sleep(800);
                        ad.add();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"c线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=1;x<11;x++){
                    try {
                        Thread.sleep(900);
                        ad.del();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"d线程").start();
    }
}
