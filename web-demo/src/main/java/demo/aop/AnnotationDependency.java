package demo.aop;

import org.springframework.stereotype.Component;

@Component("myDependency")
public class AnnotationDependency
{
    public void foo(int intValue)
    {
        System.out.println("foo(int): " + intValue);
    }

    public void bar()
    {
        System.out.println("bar()");
    }
}
