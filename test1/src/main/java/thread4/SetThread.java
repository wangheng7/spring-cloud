package thread4;

/**
 * ClassName:    SetThread
 * Package:    thread4
 * Description:
 * Datetime:    2020/12/1   21:37
 * Author:   XXXXX@XX.com
 */
public class SetThread implements Runnable{

    private Student stu;

    public SetThread(Student stu){
        this.stu = stu;
    }

    private int a = 1;

    //与GetThread用同一把锁
    @Override
    public void run() {
        while(true){
            synchronized (stu){
                //判断是否需要生产
                if(stu.flag){
                    try {
                        stu.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(a % 2 == 1){
                    //        Student stu = new Student();
                    stu.age = 10;
                    stu.name = "张三";
                }else {
                    stu.age = 20;
                    stu.name = "李四";
                }
                a++;

                //当没有时
                stu.flag = true;
                //唤醒线程
                stu.notify();
            }
        }

    }
}
