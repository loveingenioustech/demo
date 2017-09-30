package demo.Performance;

/**
 * Created by Administrator on 2017-07-26.
 */
public class OnStackDemo {
    public static void alloc(){
        byte[] b=new byte[2];
        b[0]=1;
    }

    public static void main(String[] args) {
        long b=System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
    }



}
