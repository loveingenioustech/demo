package demo.jvm;

/**
 * Created by Administrator on 2017-08-09.
 */
public class JITDemo {

    public static void met(){
        int a=0,b=0;
        b=a+b;
    }

    // -XX:CompileThreshold=1000 -XX:+PrintCompilation
    public static void main(String args[]){
        for(int i=0;i<1000;i++){
            met();
        }

    }
}
