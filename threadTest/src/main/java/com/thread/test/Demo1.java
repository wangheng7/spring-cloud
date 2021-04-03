package com.thread.test;

/**
 * ClassName:    Demo1
 * Package:    com.thead.test
 * Description:
 */
public class Demo1 {

    public static void main(String[] args){
        Thread1 t1 = new Thread1();
        Thread thread = new Thread(t1);
        thread.start();

        while (true){
            synchronized (t1){
                if(t1.flag == true){
                    System.out.println("flag="+t1.flag);
                    break;
                }
            }

        }
    }

}
