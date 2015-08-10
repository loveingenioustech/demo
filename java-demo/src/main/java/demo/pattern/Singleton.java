package demo.pattern;

import java.io.Serializable;

/**
 * 类名称：Singleton
 * 类描述：
 * 创建时间：2015-8-10 下午2:49:58
 * 修改时间：2015-8-10 下午2:49:58
 * 修改备注：
 * 
 * @version
 */
public class Singleton implements Serializable
{

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 6129522708629706278L;

    private volatile static Singleton instance = null;

    private String identifier = "1000";

    private Singleton()
    {
        // Check if we already have an instance
        if (instance != null)
        {
            throw new IllegalStateException("Hey Man, DemoSingleton" + " instance already created.");
        }
    }

    public static Singleton getInstance()
    {
        if (instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }

    /**
     * @return the identifier
     */
    public String getIdentifier()
    {
        return identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    protected Object readResolve()
    {
        return getInstance();
    }
}
