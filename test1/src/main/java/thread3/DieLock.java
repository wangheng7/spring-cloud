package thread3;

/**
 * ClassName:    DieLock
 * Package:    thread3
 * Description:
 * Datetime:    2020/12/1   20:38
 * Author:   XXXXX@XX.com
 */
public class DieLock implements Runnable{

    private boolean flag;

    public DieLock(boolean flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        if(flag){
            synchronized (LockObj.obj1){
                System.out.println("if obj1");
                synchronized (LockObj.obj2){
                    System.out.println("if obj2");
                }
            }
        }else {
            synchronized (LockObj.obj2){
                System.out.println("else obj2");
                synchronized (LockObj.obj1){
                    System.out.println("else obj1");
                }
            }
        }

    }
}
