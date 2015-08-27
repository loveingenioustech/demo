package demo.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut
{
    @Override
    public boolean matches(Method method, Class<?> targetClass)
    {
        return ("foo".equals(method.getName()));
    }

    /*
     * (non-Javadoc)
     * <p>Title: getClassFilter</p>
     * <p>Description: </p>
     * @return
     * @see
     * org.springframework.aop.support.StaticMethodMatcherPointcut#getClassFilter
     * ()
     */
    @Override
    public ClassFilter getClassFilter()
    {
        return new ClassFilter()
        {
            public boolean matches(Class<?> cls)
            {
                return (cls == BeanOne.class);
            }
        };
    }

}
