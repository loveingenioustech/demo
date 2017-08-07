package demo.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-08-07.
 */
public class ArrayListLockDemo implements Runnable{
    // ArrayList 是线程不安全的
    public static List<Integer> numberList =new ArrayList<Integer>();

    int startnum=0;
    public ArrayListLockDemo(int startnumber){
        startnum=startnumber;
    }
    @Override
    public void run() {
        int count=0;
        while(count<1000000){
            numberList.add(startnum);
            startnum+=2;
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new ArrayListLockDemo(0));
        Thread t2=new Thread(new ArrayListLockDemo(1));
        t1.start();
        t2.start();
        while(t1.isAlive() || t2.isAlive()){
            Thread.sleep(1);
        }
        System.out.println(numberList.size());
    }

}
