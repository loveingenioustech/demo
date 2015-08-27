package demo.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut
{

    /*
     * (non-Javadoc)
     * <p>Title: getClassFilter</p>
     * <p>Description: </p>
     * @return
     * @see
     * org.springframework.aop.support.DynamicMethodMatcherPointcut#getClassFilter
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

    /**
     * <p>
     * Title: matches
     * </p>
     * <p>
     * 静态检查:
     * </p>
     * 
     * @param method
     * @param targetClass
     * @return
     * @see org.springframework.aop.support.DynamicMethodMatcher#matches(java.lang
     *      .reflect.Method, java.lang.Class)
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass)
    {
        System.out.println("Static check for " + method.getName());
        return ("foo".equals(method.getName()));

    }

    /**
     * <p>
     * Title: matches
     * </p>
     * <p>
     * 根据参数来动态检查:
     * </p>
     * 
     * @param method
     * @param targetClass
     * @param args
     * @return
     * @see org.springframework.aop.MethodMatcher#matches(java.lang.reflect.Method,
     *      java.lang.Class, java.lang.Object[])
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass, Object[] args)
    {
        System.out.println("Dynamic check for " + method.getName());

        int x = ((Integer) args[0]).intValue();

        return (x != 100);
    }

}
