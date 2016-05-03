package demo.concurrency;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentHashMapDemo
{

    public static void main(String[] args) throws InterruptedException
    {
        // demoHashMap();

        // demoHashtable();

        demoCurrentHashMap();
    }

    /**
     * 非线程安全
     * 
     * @throws InterruptedException
     */
    public static void demoHashMap() throws InterruptedException
    {
        final HashMap<String, String> map = new HashMap<String, String>(2);

        long start = System.nanoTime();

        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 10000; i++)
                {
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            String key = UUID.randomUUID().toString();

                            map.put(key, "");
                            map.get(key);
                            System.out.println("Thread: " + Thread.currentThread().getName() + " running");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");

        t.start();
        t.join();

        long duration = System.nanoTime() - start;
        System.out.println("total time: " + duration);

        System.out.println(map.size());
        Thread.sleep(3000);
        System.out.println(map.size());
        Thread.sleep(3000);
        System.out.println(map.size());
        // 正常应该是10000个，实际上要小于10000，如9996
    }

    /**
     * HashTable 容器使用 synchronized 来保证线程安全，但在线程竞争激烈的情况下 HashTable 的效
     * 率非常低下。
     * 其中一次运行时间 664889562 纳秒
     * 这里初始化大小，用处不大，2， 10000差不多
     * 
     * @throws InterruptedException
     */
    public static void demoHashtable() throws InterruptedException
    {
        // final Hashtable<String, String> map = new Hashtable<String,
        // String>(2);
        final Hashtable<String, String> map = new Hashtable<String, String>(10000);

        long start = System.nanoTime();

        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 10000; i++)
                {
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            String key = UUID.randomUUID().toString();

                            map.put(key, "");
                            map.get(key);
                            System.out.println("Thread: " + Thread.currentThread().getName() + " running");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");

        t.start();
        t.join();

        long duration = System.nanoTime() - start;
        System.out.println("total time: " + duration);

        System.out.println(map.size());
        Thread.sleep(3000);
        System.out.println(map.size());
        Thread.sleep(3000);
        System.out.println(map.size());
        // 打印正常的10000个
    }

    /**
     * 锁分段技术: 首先将数据分成一段一段的存储，
     * 然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据的时候，其他段的数据也
     * 能被其他线程访问。
     * ConcurrentHashMap 是由 Segment 数组结构和 HashEntry 数组结构组成。
     * 其中一次运行时间 631580537 这里性能表现不是特别明显
     * 初始化因子比较重要，10000比2块
     * 572677500
     * 
     * @throws InterruptedException
     */
    public static void demoCurrentHashMap() throws InterruptedException
    {
        // final ConcurrentHashMap<String, String> map = new
        // ConcurrentHashMap<String, String>(2);
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>(10000);

        long start = System.nanoTime();

        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 10000; i++)
                {
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            String key = UUID.randomUUID().toString();

                            map.put(key, "");
                            map.get(key);
                            System.out.println("Thread: " + Thread.currentThread().getName() + " running");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");

        t.start();
        t.join();

        long duration = System.nanoTime() - start;
        System.out.println("total time: " + duration);

        System.out.println(map.size());
        Thread.sleep(3000);
        System.out.println(map.size());
        Thread.sleep(3000);
        System.out.println(map.size());
        // 打印正常的10000个
    }

}
