package demo.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myBean")
public class AnnotationBean
{
    private AnnotationDependency myDependency;

    public void execute()
    {
        myDependency.foo(100);
        myDependency.foo(101);
        myDependency.bar();
    }

    @Autowired
    public void setDep(AnnotationDependency myDependency)
    {
        this.myDependency = myDependency;
    }
}
