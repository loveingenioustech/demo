package demo.lock;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Vector;

/**
 * Created by Administrator on 2017-08-07.
 */
public class BiasedLockDemo {
    @NotNull
    public static List<Integer> numberList =new Vector<Integer>();
    public static void main(String[] args) throws InterruptedException {
        long begin=System.currentTimeMillis();
        int count=0;
        int startnum=0;
        while(count<10000000){
            numberList.add(startnum);
            startnum+=2;
            count++;
        }
        long end=System.currentTimeMillis();
        System.out.println(end-begin);
    }

    // 使用偏向锁
    // -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
    // 2898

    // 不使用偏向锁
    // -XX:-UseBiasedLocking
    // 3089

}
