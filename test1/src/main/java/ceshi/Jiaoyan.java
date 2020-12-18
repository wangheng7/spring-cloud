package ceshi;

/**
 * ClassName:    Jiaoyan
 * Package:    ceshi
 * Description:
 * Datetime:    2020/12/1   9:38
 * Author:   XXXXX@XX.com
 */
public class Jiaoyan {
    public static void main(String[] args){
        String flag = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";
        String ip = "192.168.187.10";
        boolean boo = ip.matches(flag);
        if(boo){
            System.out.println("正确");
        }else {
            System.out.println("错误");
        }
    }
}
