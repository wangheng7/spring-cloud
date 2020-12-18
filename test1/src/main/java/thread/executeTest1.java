package thread;

public class executeTest1 {
    public static void main(String[] args){
//        Test1 t1 = new Test1("a");
//        Test1 t2 = new Test1("b");
//        t1.start();
//        t2.start();

//        new Thread(new Test2("c")).start();
//        new Thread(new Test2("d")).start();


//        Join1 j1 = new Join1("a");
//        Join1 j2 = new Join1("b");
//        j1.start();
//        j2.start();

        /*
        加join()
         */
//        Join1 j1 = new Join1("a");
//        Join1 j2 = new Join1("b");
//        j1.start();
//        j2.start();
//        try {
//            j1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            j2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(Thread.currentThread().getName()+"主线程运行结束!");


//        Runnable r1 = new Runable1();
//        //指定线程目标
//        Thread1 t1 = new Thread1(r1);
//        t1.start();


        Runable2 r2 = new Runable2();
        Thread t = new Thread(r2);
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
            if(i == 3){
                t.start();
            }
            if(i == 8){
                r2.stopThread();
            }
        }



    }
}
