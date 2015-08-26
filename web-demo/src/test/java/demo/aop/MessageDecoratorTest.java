package demo.aop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

public class MessageDecoratorTest
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

}
