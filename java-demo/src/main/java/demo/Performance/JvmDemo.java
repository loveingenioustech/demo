package demo.Performance;

/**
 * Created by Administrator on 2017-07-31.
 */
public class JvmDemo {
    public static void main(String args[]){
//        printJvmMemory();

//        alloc1M();

//        alloc4M();

        doGC();
    }

    // 设置-Xmx20m -Xms5m
    public static void printJvmMemory(){
        System.out.print("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024+"M");

        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024+"M");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024+"M");

    }

    // 总内存不变，会尽可能维持在最小堆
    public static void alloc1M(){
        byte[] b=new byte[1*1024*1024];
        System.out.println("分配了1M空间给数组");

        printJvmMemory();
    }

    public static void alloc4M(){
        byte[] b=new byte[4*1024*1024];
        System.out.println("分配了4M空间给数组");

        printJvmMemory();
    }

    public static void doGC(){
        byte[] b=new byte[4*1024*1024];
        System.out.println("分配了4M空间给数组");

        System.out.println("Before GC");
        printJvmMemory();

        System.gc();

        System.out.println("After GC");
        printJvmMemory();
    }

}

