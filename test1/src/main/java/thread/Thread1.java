package thread;

public class Thread1 extends Thread {
    public Thread1(Runnable target) {
        super(target);
    }

    public void run(){
        System.out.println("Thread run");
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }

}
