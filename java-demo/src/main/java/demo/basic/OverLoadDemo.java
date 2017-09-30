package demo.basic;

/**
 * Created by Administrator on 2017-05-04.
 */
public class OverLoadDemo {
    public boolean equals( OverLoadDemo other ) {
        System.out.println("MethodOverrideVsOverload equals method reached" );
        return true;
    }

    /**
     * 在Java5中，新增了注解，其中包括很好用的编译时注解（compile time annotations）@override，来保证方法正确的重写了父类方法。
     * 如果在上面的方法中添加了注解，那么JVM会抛出一个编译错误。
     * @param other
     * @return
     */
    @Override
    public boolean equals( Object other ) {
        System.out.println("MethodOverrideVsOverload equals method reached" );
        return true;
    }

    public static void main(String[] args) {
        Object o1 = new OverLoadDemo();
        Object o2 = new OverLoadDemo();
        OverLoadDemo o3 = new OverLoadDemo();
        OverLoadDemo o4 = new OverLoadDemo();
        if(o1.equals(o2)){
            System.out.println("objects o1 and o2 are equal");
        }
        if(o3.equals(o4)){
            System.out.println("objects o3 and o4 are equal");
        }
    }


}
