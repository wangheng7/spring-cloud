package com.thread.test3;

/**
 * ClassName:    Add1Del1
 * Package:    com.thread.test3
 * Description:
 * Datetime:    2021/4/1   21:44
 * Author:   XXXXX@XX.com
 */
public class Add1Del1 {

    private int number = 0;

    public synchronized void add() throws InterruptedException {
        while(number != 0){
           this.wait();
        }
        number ++;
        System.out.println(Thread.currentThread().getName()+number);
        //通知
        this.notifyAll();
    }

    public synchronized void del() throws InterruptedException {
        while(number == 0){
            this.wait();
        }
        number --;
        System.out.println(Thread.currentThread().getName()+number);
        this.notifyAll();
    }

}
