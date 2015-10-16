package demo.redis;

import demo.EnvConstant;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDemo
{
    private static JedisPool pool = new JedisPool(EnvConstant.HOST_REDIS, EnvConstant.PORT_REDIS);

    private static JedisWrapper jedisWrapper = new JedisWrapper();

    public static void main(String[] args)
    {
        // testConnect();

        testJedisWrapper();
    }

    private static void testConnect()
    {
        try
        {
            Jedis jedis = pool.getResource();
            jedis.set("MSG", "Hello World");
            String result = jedis.get("MSG");
            System.out.println(" MSG : " + result);
            pool.returnResource(jedis);
        }
        catch (Exception e)
        {
            System.err.println(e.toString());
        }
        finally
        {
            pool.destroy();
        }
    }

    private static void testJedisWrapper()
    {
        jedisWrapper.set("MSG", "Hello world 2 ");
        String result = jedisWrapper.get("MSG");
        System.out.println("MSG : " + result);
    }
}
