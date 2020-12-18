package thread3;

/**
 * ClassName:    LockTest
 * Package:    thread3
 * Description:
 * Datetime:    2020/12/1   20:57
 * Author:   XXXXX@XX.com
 */
public class LockTest {
    public static void main(String[] args){
        DieLock dl = new DieLock(true);
        DieLock d2 = new DieLock(false);

        dl.run();
        d2.run();
    }
}
