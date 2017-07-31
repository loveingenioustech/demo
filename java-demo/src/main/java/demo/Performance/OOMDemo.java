package demo.Performance;

import java.util.Vector;

/**
 * Created by Administrator on 2017-07-31.
 */
public class OOMDemo {

    // VM 参数 -Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\tmp\OOMDemo.dump
    // 把线程 dump 出来  -XX:OnOutOfMemoryError="D:/Program Files/Java/jdk1.8.0_60/bin/printstack.bat %p"
    // -Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\tmp\OOMDemo.dump -XX:OnOutOfMemoryError="F:\github\loveingenioustech\demo\java-demo\src\main\java\demo\Performance\printstack.bat %p"
    public static void main(String args[]){
        Vector v=new Vector();

        for(int i=0;i<25;i++){
            v.add(new byte[1*1024*1024]);
        }
    }

}
