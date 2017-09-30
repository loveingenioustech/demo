package demo.jvm;

/**
 * Created by Administrator on 2017-08-03.
 */
public class AppLoaderDemo {
    public static void main(String args[]){
        AppLoaderDemo appLoaderDemo = new AppLoaderDemo();
        appLoaderDemo.print();
    }

    public void print(){
        System.out.println("I am in apploader");
    }

      // 放开注释，把class 文件拷贝到其他目录
//    public void print(){
//        System.out.println("I am in bootloader");
//    }

}
