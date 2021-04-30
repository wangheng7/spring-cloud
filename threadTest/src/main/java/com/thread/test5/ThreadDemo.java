package com.thread.test5;

import java.util.concurrent.Callable;

/**
 * ClassName:    ThreadDemo
 * Package:    com.thread.test5
 * Description:
 */
public class ThreadDemo implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("test5");
        return "return 结果";
    }
}
