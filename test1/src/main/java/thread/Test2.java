package thread;

public class Test2 implements Runnable {
    private String name;

    public Test2(String name){
        this.name = name;
    }

    public void run(){
        for(int i=0;i<6;i++){
            System.out.println(name+"运行:"+i);
            try {
                Thread.sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
