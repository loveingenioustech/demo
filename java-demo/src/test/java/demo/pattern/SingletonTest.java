package demo.pattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SingletonTest
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
    public void testGetInstance()
    {
        Singleton.getInstance();
    }

    @Test
    public void testReflection()
    {
        Singleton instanceOne = Singleton.getInstance();

        Class clazz = Singleton.class;

        Constructor cons;
        try
        {
            cons = clazz.getDeclaredConstructor();
            cons.setAccessible(true);

            Singleton instanceTwo = (Singleton) cons.newInstance();

            instanceTwo.setIdentifier("2000");

            System.out.println("This is the value of instance one: " + instanceOne.getIdentifier());
            System.out.println("This is the value of instance two: " + instanceTwo.getIdentifier());

        }
        catch (SecurityException e)
        {
            System.out.println("---SecurityException---");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            System.out.println("---NoSuchMethodException---");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("---IllegalArgumentException---");
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            System.out.println("---InstantiationException---");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            System.out.println("---IllegalAccessException---");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            // 会进这个异常块
            System.out.println("---InvocationTargetException---");
            e.printStackTrace();
        }
    }

    @Test
    public void testSecurityManager()
    {
        Singleton instanceOne = Singleton.getInstance();

        SecurityManager mgr = new SecurityManager();
        System.setSecurityManager(mgr);

        Class clazz = Singleton.class;

        Constructor cons;
        try
        {
            cons = clazz.getDeclaredConstructor();
            cons.setAccessible(true);

            Singleton instanceTwo = (Singleton) cons.newInstance();

            instanceTwo.setIdentifier("2000");

            System.out.println("This is the value of instance one: " + instanceOne.getIdentifier());
            System.out.println("This is the value of instance two: " + instanceTwo.getIdentifier());

        }
        catch (SecurityException e)
        {
            System.out.println("---SecurityException---");
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            System.out.println("---NoSuchMethodException---");
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("---IllegalArgumentException---");
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            System.out.println("---InstantiationException---");
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            System.out.println("---IllegalAccessException---");
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            System.out.println("---InvocationTargetException---");
            e.printStackTrace();
        }
    }

    @Test
    public void testDeserialize()
    {
        Singleton instanceOne = Singleton.getInstance();

        try
        {
            // Serialize to a file
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
            out.writeObject(instanceOne);
            out.close();

            instanceOne.setIdentifier("2000");

            // Serialize to a file
            ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
            Singleton instanceTwo = (Singleton) in.readObject();
            in.close();

            System.out.println("This is the value of instance one: " + instanceOne.getIdentifier());
            System.out.println("This is the value of instance two: " + instanceTwo.getIdentifier());

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}
