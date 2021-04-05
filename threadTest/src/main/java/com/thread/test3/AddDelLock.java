package com.thread.test3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AddDelLock {

    private int number = 0;

    public Lock lock = new ReentrantLock();

    public Condition condition = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            while(number != 0){
                condition.await();
            }
            number ++;
            System.out.println(Thread.currentThread().getName()+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void del() throws InterruptedException {
        lock.lock();
        try {
            while(number == 0){
                condition.await();
            }
            number --;
            System.out.println(Thread.currentThread().getName()+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
