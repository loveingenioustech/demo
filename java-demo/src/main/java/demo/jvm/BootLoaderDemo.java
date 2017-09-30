package demo.jvm;

/**
 * Created by Administrator on 2017-08-03.
 *
 *
 */
public class BootLoaderDemo {
    // java demo.jvm.BootLoaderDemo -Xbootclasspath/a:D:/tmp/clz
    public static void main(String args[]){
        AppLoaderDemo appLoaderDemo = new AppLoaderDemo();
        appLoaderDemo.print();
    }


}
