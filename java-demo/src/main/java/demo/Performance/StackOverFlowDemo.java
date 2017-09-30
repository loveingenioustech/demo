package demo.Performance;

/**
 * Created by Administrator on 2017-07-31.
 */
public class StackOverFlowDemo {

    private static int count=0;
    public static void recursion(long a,long b,long c){
        long e=1,f=2,g=3,h=4,i=5,k=6,q=7,x=8,y=9,z=10;
        count++;
        recursion(a,b,c);
    }

    public static void main(String args[]){
        try{
            recursion(0L,0L,0L);
        }catch(Throwable e){
            System.out.println("deep of calling = "+count);
            e.printStackTrace();
        }
    }

    // 指定VM参数
    // -Xss128K
    // deep of calling = 298
    // java.lang.StackOverflowError

    // -Xss256K
    // deep of calling = 1566
    // java.lang.StackOverflowError

}
