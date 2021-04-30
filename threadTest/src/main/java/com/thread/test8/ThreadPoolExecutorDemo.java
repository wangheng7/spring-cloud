package com.thread.test8;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName:    ThreadPoolExecutorDemo
 * Package:    com.thread.test8
 * Description:
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args){
//        ExecutorService threadpool = Executors.newSingleThreadExecutor();
//        ExecutorService threadpool = Executors.newFixedThreadPool(5);
        ExecutorService threadpool = Executors.newCachedThreadPool();

        try{
            for(int x=1;x<=10;x++){
                threadpool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+"办业务");
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadpool.shutdown();
        }
    }
}
