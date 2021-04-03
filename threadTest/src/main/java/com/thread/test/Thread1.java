package com.thread.test;

/**
 * ClassName:    Thread1
 * Package:    com.thead.test
 * Description:
 */
public class Thread1 implements Runnable{

    public boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("改后"+flag);
    }
}
