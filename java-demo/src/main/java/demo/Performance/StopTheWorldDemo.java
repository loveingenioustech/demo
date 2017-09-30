package demo.Performance;

/**
 * Created by Administrator on 2017-08-01.
 */
public class StopTheWorldDemo {
    public static void main(String args[]){
        PrintThread p1 = new PrintThread();
        p1.start();

        MyThread m1 = new MyThread();
        m1.start();
    }

}
