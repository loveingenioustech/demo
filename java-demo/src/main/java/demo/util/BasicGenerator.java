package demo.util;

/**
 * 类名称：BasicGenerator
 * 类描述：基本生成器
 * 创建时间：2016年4月25日 下午4:04:21
 * 修改时间：2016年4月25日 下午4:04:21
 * 修改备注：
 * 
 * @version
 */
public class BasicGenerator<T> implements Generator<T>
{
    private Class<T> type;

    public BasicGenerator(Class<T> type)
    {
        this.type = type;
    }

    public T next()
    {
        try
        {
            // Assumes type is a public class:
            return type.newInstance();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    // Produce a Default generator given a type token:
    public static <T> Generator<T> create(Class<T> type)
    {
        return new BasicGenerator<T>(type);
    }
}
