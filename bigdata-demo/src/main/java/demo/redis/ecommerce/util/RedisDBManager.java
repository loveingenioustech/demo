package demo.redis.ecommerce.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import demo.EnvConstant;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDBManager
{
    private static Date date = new Date();
    private static int minimum = 1;
    private static int maximum = 100000000;

    // going with the default pool.
    private static JedisPool connectionPool = new JedisPool(EnvConstant.HOST_REDIS, EnvConstant.PORT_REDIS);

    /**
     * 获取连接
     * 
     * @return
     */
    public Jedis getConnection()
    {
        return connectionPool.getResource();
    }

    /**
     * 释放连接
     * 
     * @param jedis
     */
    public void returnConnection(Jedis jedis)
    {
        connectionPool.returnResource(jedis);
    }

    public static String getDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateValue = dateFormat.format(date);
        return dateValue;
    }

    public static String getRandomSessionID()
    {
        int randomNum = minimum + (int) (Math.random() * maximum);
        return new Integer(randomNum).toString();
    }
}
