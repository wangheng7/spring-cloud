package com.thread.test5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ClassName:    CallableDemo
 * Package:    com.thread.test3
 * Description:
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new ThreadDemo());
        new Thread(task,"name1").start();

        System.out.println(task.get());
    }
}
