package demo.aop;

import demo.annotation.AdviceRequired;

public class BeanOne
{
    public void foo()
    {
        System.out.println("foo");
    }

    public void foo(int x)
    {
        System.out.println("Invoked foo() with: " + x);
    }

    public void foo1()
    {
        System.out.println("foo1");
    }

    public void foo2()
    {
        System.out.println("foo2");
    }

    @AdviceRequired
    public void foo3(int x)
    {
        System.out.println("Invoked foo() with: " + x);
    }

    public void bar()
    {
        System.out.println("bar");
    }

    public void yup()
    {
        System.out.println("yup");
    }
}
