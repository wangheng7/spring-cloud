package thread;

public class Test1 extends Thread{
    private String name;

    public Test1(String name) {
        this.name = name;
    }

    public void run(){
        for(int i=0;i<6;i++){
            System.out.println(name+"运行:"+i);
            try {
                sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
