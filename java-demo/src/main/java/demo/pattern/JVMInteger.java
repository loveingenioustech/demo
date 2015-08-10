package demo.pattern;

/**
 * 一个典型的flyweight 模式在字符串对象创建中的应用。这个模式通过减少对象的创建来节约内存。String对象会创建一个字符串池（a pool of
 * string），如果当前准备新创建的字符串对象的值在这个池子中已经存在，那么就不会生成新对象，而是复用池中已有的字符串对象。flyweight
 * 模式的精髓就是对象复用。
 * 享元模式：FLYWEIGHT在拳击比赛中指最轻量级。享元模式以共享的方式高效的支持大量的细粒度对象。享元模式能做到共享的关键是区分内蕴状态和外蕴状态。
 * 内蕴状态存储在享元内部，不会随环境的改变而有所不同。外蕴状态是随环境的改变而改变的。外蕴状态不能影响内蕴状态，它们是相互独立的。
 * 将可以共享的状态和不可以共享的状态从常规类中区分开来
 * ，将不可以共享的状态从类里剔除出去。客户端不可以直接创建被共享的对象，而应当使用一个工厂对象负责创建被共享的对象。享元模式大幅度的降低内存中对象的数量。
 * 类名称：JVMInteger
 * 类描述：
 * 创建时间：2015-8-10 下午2:45:51
 * 修改时间：2015-8-10 下午2:45:51
 * 修改备注：
 * 
 * @version
 */
public class JVMInteger
{

    /*
     * static {
     * // setup of the cache size omitted
     * for (int k = 0; k < cache.length; k++)
     * cache[k] = new Integer(j++);
     * }
     * public static Integer valueOf(int i) {
     * assert IntegerCache.high >= 127;
     * if (i >= IntegerCache.low && i <= IntegerCache.high)
     * return IntegerCache.cache[i + (-IntegerCache.low)];
     * return new Integer(i);
     * }
     */

}
