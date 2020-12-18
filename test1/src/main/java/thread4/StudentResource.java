package thread4;

/**
 * ClassName:    StudentResource
 * Package:    thread4
 * Description:
 * Datetime:    2020/12/1   21:35
 * Author:   XXXXX@XX.com
 */
public class StudentResource {
    public static void main(String[] args){
        Student stu = new Student();

        GetThread get = new GetThread(stu);
        SetThread set = new SetThread(stu);

        Thread t1 = new Thread(get);
        Thread t2 = new Thread(set);

        t1.start();
        t2.start();
    }
}
