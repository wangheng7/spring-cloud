package com.thread.test5;

import java.util.concurrent.Semaphore;

/**
 * ClassName:    SemaphoreDemo
 * Package:    com.thread.test5
 * Description:
 */
public class SemaphoreDemo {

    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(3);

        for(int x=1;x<=6;x++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //占用
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName()+"：抢到车位");
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName()+"：离开车位");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        //释放
                        semaphore.release();
                    }

                }
            }, String.valueOf(x)).start();
        }
    }
}
