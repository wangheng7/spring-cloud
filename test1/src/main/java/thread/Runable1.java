package thread;

public class Runable1 implements Runnable {
    public void run(){
        System.out.println("Runnable run");
        for(int j=0;j<10;j++){
            System.out.println(Thread.currentThread().getName()+":"+j);
        }
    }

}
