package demo.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
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
