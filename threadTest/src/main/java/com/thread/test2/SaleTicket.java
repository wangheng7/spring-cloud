package com.thread.test2;

/**
 * ClassName:    SaleTicket
 * Package:    com.thread.test2
 * Description:
 * Datetime:    2021/3/31   20:44
 * Author:   XXXXX@XX.com
 */
public class SaleTicket {

    public static void main(String[ ] args){

        Ticket2 ticket = new Ticket2();
        //Thread(Runnable target,String name)
        //匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x = 1;x<11;x++){
                    ticket.sale();
                }
            }
        },"thread1").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x = 1;x<11;x++){
                    ticket.sale();
                }
            }
        },"thread2").start();
    }
}
