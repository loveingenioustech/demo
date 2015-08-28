package demo.aop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopXmlTest
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
    public void testProxyFactoryBean()
    {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/aop-context-xml.xml");
        ctx.refresh();

        DependencyBean bean1 = (DependencyBean) ctx.getBean("myBean1");
        DependencyBean bean2 = (DependencyBean) ctx.getBean("myBean2");

        System.out.println("Bean 1");
        bean1.execute();

        System.out.println("\nBean 2");
        bean2.execute();
    }

    @Test
    public void testIntroductionConfig()
    {
        // aop namespace 貌似不能与编程方式一起使用，所以分开了两个文件
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/aop-context-xml.xml");
        ctx.refresh();

        TargetBean bean = (TargetBean) ctx.getBean("bean");
        IsModified mod = (IsModified) bean;

        System.out.println("Is TargetBean?: " + (bean instanceof TargetBean));
        System.out.println("Is IsModified?: " + (bean instanceof IsModified));

        System.out.println("Has been modified?: " + mod.isModified());
        bean.setName("Chris Schaefer");

        System.out.println("Has been modified?: " + mod.isModified());
        bean.setName("Clarence Ho");

        System.out.println("Has been modified?: " + mod.isModified());
    }

    @Test
    public void testAopNamespace()
    {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/aop-namespace-xml.xml");
        ctx.refresh();

        DependencyBean myBean = (DependencyBean) ctx.getBean("myBean");
        myBean.execute();
    }
}
