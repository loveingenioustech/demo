package demo.aop;

import org.aopalliance.aop.Advice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

import demo.annotation.AdviceRequired;

public class AopTest
{

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testProxyFactory()
    {
        MessageWriter target = new MessageWriter();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new MessageDecorator());
        pf.setTarget(target);

        MessageWriter proxy = (MessageWriter) pf.getProxy();

        target.writeMessage("这是直接方法调用");
        System.out.println("");
        proxy.writeMessage("通过代理调用");

    }

    @Test
    public void testBeforeAdvice()
    {
        MessageWriter target = new MessageWriter();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SimpleBeforeAdvice());
        pf.setTarget(target);

        MessageWriter proxy = (MessageWriter) pf.getProxy();

        proxy.writeMessage("测试 BeforeAdvice ");
    }

    @Test
    public void testAroudAdvice()
    {
        WorkerBean target = new WorkerBean();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(new ProfilingInterceptor());

        WorkerBean proxy = (demo.aop.WorkerBean) factory.getProxy();

        proxy.doSomeWork(10000);
    }

    @Test
    public void testStaticPointcut()
    {
        BeanOne one = new BeanOne();
        BeanTwo two = new BeanTwo();

        BeanOne proxyOne;
        BeanTwo proxyTwo;

        Pointcut pc = new SimpleStaticPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);

        // 只有 BeanOne 会匹配，因为有 ClassFilter
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(one);
        proxyOne = (BeanOne) pf.getProxy();

        pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(two);
        proxyTwo = (BeanTwo) pf.getProxy();

        proxyOne.foo();
        proxyTwo.foo();

        proxyOne.bar();
        proxyTwo.bar();
    }

    @Test
    public void testDynamicPointcut()
    {
        BeanOne target = new BeanOne();

        Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        BeanOne proxy = (BeanOne) pf.getProxy();

        proxy.foo(1);
        proxy.foo(10);
        // 动态匹配，不等于100就是匹配的
        proxy.foo(100);

        proxy.bar();
        proxy.bar();
        proxy.bar();
    }

    @Test
    public void testNamePointcut()
    {
        BeanOne target = new BeanOne();
        NameMatchMethodPointcut pc = new NameMatchMethodPointcut();
        pc.addMethodName("foo");
        pc.addMethodName("bar");

        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        BeanOne proxy = (BeanOne) pf.getProxy();
        proxy.foo();
        proxy.foo(999);
        proxy.bar();
        proxy.yup();
    }

    @Test
    public void testRegexPointcut()
    {
        BeanOne target = new BeanOne();
        JdkRegexpMethodPointcut pc = new JdkRegexpMethodPointcut();
        pc.setPattern(".*foo.*");
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        BeanOne proxy = (BeanOne) pf.getProxy();

        proxy.foo1();
        proxy.foo2();
        proxy.bar();
    }

    @Test
    public void testAspectjexpPointcut()
    {
        BeanOne target = new BeanOne();
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression("execution(* foo*(..))");
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        BeanOne proxy = (BeanOne) pf.getProxy();
        proxy.foo1();
        proxy.foo2();
        proxy.bar();
    }

    @Test
    public void testAnnotationPointcut()
    {
        BeanOne target = new BeanOne();

        AnnotationMatchingPointcut pc = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        BeanOne proxy = (BeanOne) pf.getProxy();

        proxy.foo3(100);
        proxy.bar();
    }

    @Test
    public void testNameMatchMethodPointcutAdvisor()
    {
        BeanOne target = new BeanOne();

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(new SimpleAdvice());
        advisor.addMethodName("foo");
        advisor.addMethodName("bar");
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        BeanOne proxy = (BeanOne) pf.getProxy();
        proxy.foo();
        proxy.foo(999);
        proxy.bar();
        proxy.yup();
    }

    @Test
    public void testIntroduction()
    {
        TargetBean target = new TargetBean();
        target.setName("Chris Schaefer");

        IntroductionAdvisor advisor = new IsModifiedAdvisor();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setOptimize(true);

        TargetBean proxy = (TargetBean) pf.getProxy();
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("Is TargetBean?: " + (proxy instanceof TargetBean));
        System.out.println("Is IsModified?: " + (proxy instanceof IsModified));

        System.out.println("Has been modified?: " + proxyInterface.isModified());

        proxy.setName("Chris Schaefer");

        System.out.println("Has been modified?: " + proxyInterface.isModified());

        proxy.setName("Clarence Ho");

        System.out.println("Has been modified?: " + proxyInterface.isModified());

    }

}
