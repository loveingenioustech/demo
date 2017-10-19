package demo.pattern;

import demo.io.Employee;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
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

    @Nullable
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

    @Nullable
    protected Object readResolve()
    {
        return getInstance();
    }
}


/**
 * 基于类初始化的解决方案
 * JVM 在类的初始化阶段（即在 Class 被加载后，且被线程使用之前），会执行类的初始化。
 * 在执行类的初始化期间， JVM 会去获取一个锁。这个锁可以同步多个线程对同一个类的初始化。
 */
class InstanceFactory {

    private static class InstanceHolder {

        public static Employee instance = new Employee();

    }

    public static Employee getInstance() {

        return InstanceHolder.instance; //这里将导致 InstanceHolder 类被初始化

    }
}

/**
 * 默认枚举实例的创建是线程安全的，但是在枚举中的其他任何方法由程序员自己负责。
 * 通过EasySingleton.INSTANCE来访问
 */
enum EasySingleton{
    INSTANCE;
}

/**
 * getInstance()方法要检查两次，确保是否实例INSTANCE是否为null或者已经实例化了，这也是为什么叫double checked locking 模式。
 */
class DoubleCheckedLockingSingleton{
    private volatile DoubleCheckedLockingSingleton INSTANCE;

    private DoubleCheckedLockingSingleton(){}

    public DoubleCheckedLockingSingleton getInstance(){
        if(INSTANCE == null){
            synchronized(DoubleCheckedLockingSingleton.class){

                //double checking Singleton instance
                if(INSTANCE == null){
                    INSTANCE = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return INSTANCE;
    }
}


/**
 * 单例是静态的final变量，当类第一次加载到内存中的时候就初始化了，所以创建的实例固然是thread-safe。
 */
class StaticSingleton{
    //initailzed during class loading
    private static final StaticSingleton INSTANCE = new StaticSingleton();
    //to prevent creating another instance of Singleton
    private StaticSingleton(){}
    public static StaticSingleton getSingleton(){
        return INSTANCE;
    }
}
