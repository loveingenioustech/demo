package demo.redis;

import demo.EnvConstant;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisWrapper
{
    static JedisPool pool = new JedisPool(EnvConstant.HOST_REDIS, EnvConstant.PORT_REDIS);

    public void set(String key, String value)
    {
        Jedis jedis = pool.getResource();
        jedis.set(key, value);
        pool.returnResource(jedis);
    }

    public String get(String key)
    {
        Jedis jedis = pool.getResource();
        String result = jedis.get("MSG");
        pool.returnResource(jedis);
        return result;
    }
}
