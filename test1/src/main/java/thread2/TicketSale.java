package thread2;

public class TicketSale implements Runnable{

    public TicketSale(String name) {
        super();
    }

    static int ticket = 10;

    public void run() {
        while (ticket > 0){
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + "卖出了第" + ticket + "张票");
                ticket --;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
