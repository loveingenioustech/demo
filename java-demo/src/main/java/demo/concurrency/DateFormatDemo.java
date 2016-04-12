package demo.concurrency;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateFormatDemo
{
    private final DateFormat format = new SimpleDateFormat("yyyyMMdd");

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>()
    {
        @Override
        protected DateFormat initialValue()
        {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    private final DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyyMMdd");

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        DateFormatDemo demo = new DateFormatDemo();

        // demo.demoConvert();

        // demo.demoSyncConvert();

        // demo.demoThreadLocalConvert();

        demo.demoJodaConvert();
    }

    /**
     * 有时候，它输出正确的日期，有时候会输出错误，有些时候甚至会抛出NumberFormatException
     * 
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public void demoConvert() throws InterruptedException, ExecutionException
    {
        Callable<Date> task = new Callable<Date>()
        {

            @Override
            public Date call() throws Exception
            {
                return convert("20160412");
            }
        };

        // 尝试2个线程的情况
        ExecutorService exec = Executors.newFixedThreadPool(2);

        List<Future<Date>> results = new ArrayList<Future<Date>>();

        // 实现5次日期转换
        for (int i = 0; i < 5; i++)
        {
            results.add(exec.submit(task));
        }

        exec.shutdown();

        // 查看结果
        for (Future<Date> result : results)
        {
            System.out.println(result.get());
        }
    }

    public void demoSyncConvert() throws InterruptedException, ExecutionException
    {
        Callable<Date> task = new Callable<Date>()
        {

            @Override
            public Date call() throws Exception
            {
                return syncConvert("20160412");
            }
        };

        // 尝试5个线程的情况
        ExecutorService exec = Executors.newFixedThreadPool(5);

        List<Future<Date>> results = new ArrayList<Future<Date>>();

        // 实现5次日期转换
        for (int i = 0; i < 10; i++)
        {
            results.add(exec.submit(task));
        }

        exec.shutdown();

        // 查看结果
        for (Future<Date> result : results)
        {
            System.out.println(result.get());
        }
    }

    public void demoThreadLocalConvert() throws InterruptedException, ExecutionException
    {
        Callable<Date> task = new Callable<Date>()
        {

            @Override
            public Date call() throws Exception
            {
                return localConvert("20160412");
            }
        };

        // 尝试5个线程的情况
        ExecutorService exec = Executors.newFixedThreadPool(5);

        List<Future<Date>> results = new ArrayList<Future<Date>>();

        // 实现5次日期转换
        for (int i = 0; i < 10; i++)
        {
            results.add(exec.submit(task));
        }

        exec.shutdown();

        // 查看结果
        for (Future<Date> result : results)
        {
            System.out.println(result.get());
        }
    }

    public void demoJodaConvert() throws InterruptedException, ExecutionException
    {
        Callable<Date> task = new Callable<Date>()
        {

            @Override
            public Date call() throws Exception
            {
                return jodaConvert("20160412");
            }
        };

        // 尝试5个线程的情况
        ExecutorService exec = Executors.newFixedThreadPool(5);

        List<Future<Date>> results = new ArrayList<Future<Date>>();

        // 实现5次日期转换
        for (int i = 0; i < 10; i++)
        {
            results.add(exec.submit(task));
        }

        exec.shutdown();

        // 查看结果
        for (Future<Date> result : results)
        {
            System.out.println(result.get());
        }
    }

    public Date convert(String source) throws ParseException
    {
        return format.parse(source);
    }

    public Date syncConvert(String source) throws ParseException
    {
        synchronized (format)
        {
            return format.parse(source);
        }
    }

    public Date localConvert(String source) throws ParseException
    {
        // 另外一个方法就是使用ThreadLocal变量去容纳DateFormat对象，也就是说每个线程都有一个属于自己的副本，并无需等待其他线程去释放它。
        // 这种方法会比使用同步块更高效。
        return df.get().parse(source);
    }

    public Date jodaConvert(String source)
    {
        return dtf.parseDateTime(source).toDate();
    }
}
