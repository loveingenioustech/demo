package demo.test.perf;

/**
 * 类名称：抽象类 PerfTest
 * 类描述： 泛型是Java SE
 * 1.5的新特性，泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。这种参数类型可以用在类、接口和方法的创建中，分别称为泛型类、泛型接口、
 * 泛型方法。 Java语言引入泛型的好处是安全简单。
 * 创建时间：2016年4月25日 上午11:31:11
 * 修改时间：2016年4月25日 上午11:31:11
 * 修改备注：
 * 
 * @version
 */
public abstract class PerfTest<C>
{
    String name;

    public PerfTest(String name)
    {
        this.name = name;
    }

    /**
     * Override this method for different tests.
     * Returns actual number of repetitions of test.
     * 
     * @param container 实际需要测试的目标类
     * @param tp 测试参数
     * @return
     */
    public abstract int test(C container, PerfTestParam tp);

}
