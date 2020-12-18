package thread;

public class Join1 extends Thread {

    private String name;

    public Join1(String name) {
        super(name);
        this.name = name;
    }

    public void run(){
        for(int i=0;i<5;i++){
            System.out.println(name + "运行:" + i);
            try {
                sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"线程运行结束!");
    }
}
