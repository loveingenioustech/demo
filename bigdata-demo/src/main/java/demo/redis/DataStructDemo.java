package demo.redis;

import demo.EnvConstant;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class DataStructDemo
{

    private static JedisPool pool = new JedisPool(EnvConstant.HOST_REDIS, EnvConstant.PORT_REDIS);
    static Jedis jedis = null;

    public static Jedis getResource()
    {
        jedis = pool.getResource();
        return jedis;
    }

    public static void setResource(Jedis jedis)
    {
        pool.returnResource(jedis);
    }

    public static void main(String[] args) throws InterruptedException
    {
        testString();
    }

    private static void testString() throws InterruptedException
    {
        Jedis jedis = getResource();
        String commonkey = "mykey";
        jedis.set(commonkey, "Hello World");
        System.out.println("1) " + jedis.get("mykey"));

        jedis.append(commonkey, " and this is a bright sunny day ");
        System.out.println("2) " + jedis.get("mykey"));

        String substring = jedis.getrange(commonkey, 0, 5);
        System.out.println("3) " + "substring value = " + substring);

        String commonkey1 = "mykey1";
        jedis.set(commonkey1, "Let's learn redis");
        for (String value : jedis.mget(commonkey, commonkey1))
        {
            System.out.println("4) " + " - " + value);
        }

        jedis.mset("mykey2", "let's start with string", "mykey3", "then we will learn other data types");
        for (String value : jedis.mget(commonkey, commonkey1, "mykey2", "mykey3"))
        {
            System.out.println("5) " + "   -- " + value);
        }

        jedis.msetnx("mykey4", "next in line is hashmaps");
        System.out.println("6) " + jedis.get("mykey4"));

        jedis.msetnx("mykey4", "next in line is sorted sets");
        System.out.println("7) " + jedis.get("mykey4"));

        jedis.psetex("mykey5", 1000, "this message will self destruct in 1000 milliseconds");
        System.out.println("8) " + jedis.get("mykey5"));
       
        Thread.currentThread().sleep(1200);
        System.out.println("8) " + jedis.get("mykey5"));

        Long length = jedis.strlen(commonkey);
        System.out.println("9) " + " the length of the string 'mykey' is " + length);

        setResource(jedis);
    }

}
