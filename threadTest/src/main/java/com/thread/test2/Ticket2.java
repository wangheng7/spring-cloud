package com.thread.test2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName:    Ticket2
 * Package:    com.thread.test2
 * Description:
 * Datetime:    2021/3/31   21:23
 * Author:   XXXXX@XX.com
 */
public class Ticket2 {

    private int number = 10;

    private Lock lo = new ReentrantLock();

    public void sale(){

        lo.lock();
        try {
            if(number > 0){
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"卖出票数："+number-- +"剩余票数："+ number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lo.unlock();
        }

    }
}
