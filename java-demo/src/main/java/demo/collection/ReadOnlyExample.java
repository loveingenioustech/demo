package demo.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Collections类里实现了不可变结合，它通过装饰模式实现了对一般集合的封装。
 *
 * Created by Administrator on 2017-04-25.
 */
public class ReadOnlyExample {
    public static void main(String args[ ]) {
        Set<String> set = new HashSet<String>( );
        set.add("Java");
        set.add("JEE");
        set.add("Spring");
        set.add("Hibernate");
        set = Collections.unmodifiableSet(set);
        set.add("Ajax");   // not allowed. will report Exception in thread "main" java.lang.UnsupportedOperationException
    }

}
