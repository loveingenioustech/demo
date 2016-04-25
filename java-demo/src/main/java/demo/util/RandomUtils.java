package demo.util;

import java.util.ArrayList;

public class RandomUtils
{
    // Fill an existing array:
    public static <T> T[] array(T[] a, Generator<T> gen)
    {
        return new CollectionData<T>(gen, a.length).toArray(a);
    }

    // Create a new array:
    @SuppressWarnings("unchecked")
    public static <T> T[] array(Class<T> type, Generator<T> gen, int size)
    {
        T[] a = (T[]) java.lang.reflect.Array.newInstance(type, size);
        return new CollectionData<T>(gen, size).toArray(a);
    }

}

/**
 * 类名称：CollectionData
 * 类描述：集合数据
 * 创建时间：2016年4月25日 下午4:04:42
 * 修改时间：2016年4月25日 下午4:04:42
 * 修改备注：
 * 
 * @version
 */
class CollectionData<T> extends ArrayList<T>
{
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 3067323535457803468L;

    public CollectionData(Generator<T> gen, int quantity)
    {
        for (int i = 0; i < quantity; i++)
            add(gen.next());
    }

    // A generic convenience method:
    public static <T> CollectionData<T> list(Generator<T> gen, int quantity)
    {
        return new CollectionData<T>(gen, quantity);
    }
}

