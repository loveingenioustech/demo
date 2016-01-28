package demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

public class ProxyDemo
{

    public static void main(String[] args)
    {
        Object[] elements = new Object[1000];

        // fill elements with proxies for the integers 1 ... 1000
        for (int i = 0; i < elements.length; i++)
        {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            // 每次执行compare 都会记录
            Object proxy = Proxy.newProxyInstance(null, new Class[] { Comparable.class }, handler);
            elements[i] = proxy;
        }

        // construct a random integer
        Integer key = new Random().nextInt(elements.length) + 1;
        System.out.println("Key: " + key);

        // search for the key
        int result = Arrays.binarySearch(elements, key);
        System.out.println("Result: " + result);

        // print match if found
        if (result >= 0)
        {
            System.out.println(elements[result]);
        }

    }

}
