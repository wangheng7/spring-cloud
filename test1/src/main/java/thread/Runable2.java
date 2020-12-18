package thread;

public class Runable2 implements Runnable {
    private boolean stop;

    public void run(){
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+":::"+i);
        }
    }

    public void stopThread(){
        this.stop = false;
    }
}
