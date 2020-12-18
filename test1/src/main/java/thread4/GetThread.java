package thread4;

/**
 * ClassName:    GetThread
 * Package:    thread4
 * Description:
 * Datetime:    2020/12/1   21:39
 * Author:   XXXXX@XX.com
 */
public class GetThread implements Runnable{

    private Student stu;

    public GetThread(Student stu){
        this.stu = stu;
    }

    //与SetThread用同一把锁
    @Override
    public void run() {
        while (true){
            synchronized (stu){
                //没有可消费的就等待
                if(!stu.flag){
                    try {
                        //当等待时，立即释放锁。将来是从这里醒过来
                        stu.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //        Student s = new Student();
                System.out.println(stu.name+"---"+stu.age);

                stu.flag = false;
                //唤醒不能立刻执行，还得抢cpu的执行权
                stu.notify();
            }
        }

    }
}
