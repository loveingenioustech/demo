package demo.aop;

public class DependencyBean
{
    private BeanOne dep;

    public void execute()
    {
        dep.foo();
        dep.bar();
        dep.foo(100);
        dep.foo(101);
    }

    /**
     * @param dep the dep to set
     */
    public void setDep(BeanOne dep)
    {
        this.dep = dep;
    }

}
