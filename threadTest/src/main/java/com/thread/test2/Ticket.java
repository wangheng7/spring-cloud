package com.thread.test2;

/**
 * ClassName:    Ticket
 * Package:    com.thread.test2
 * Description:
 * Datetime:    2021/3/31   20:44
 * Author:   XXXXX@XX.com
 */
public class Ticket {

    private int number = 10;

    public synchronized void sale(){
        if(number > 0){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"卖出票数："+number-- +"剩余票数："+ number);
        }
    }
}
