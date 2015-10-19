package demo.redis;

import java.util.HashMap;
import java.util.Map;

import demo.EnvConstant;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
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
        // testString();

        // testHash();

        // testList();

        // testSet();

        testSortedSet();
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

    private static void testHash()
    {
        Jedis jedis = getResource();
        String commonkey = "learning redis";
        jedis.hset(commonkey, "publisher", "Packt Publisher");
        jedis.hset(commonkey, "author", "Vinoo Das");
        System.out.println(jedis.hgetAll(commonkey));

        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("ISBN", "XX-XX-XX-XX");
        attributes.put("tags", "Redis,NoSQL");
        attributes.put("pages", "250");
        attributes.put("weight", "200.56");
        jedis.hmset(commonkey, attributes);

        System.out.println(jedis.hgetAll(commonkey));

        System.out.println(jedis.hget(commonkey, "publisher"));

        System.out.println(jedis.hmget(commonkey, "publisher", "author"));

        System.out.println(jedis.hvals(commonkey));

        System.out.println(jedis.hget(commonkey, "publisher"));

        System.out.println(jedis.hkeys(commonkey));

        System.out.println(jedis.hexists(commonkey, "cost"));

        System.out.println(jedis.hlen(commonkey));

        System.out.println(jedis.hincrBy(commonkey, "pages", 10));

        System.out.println(jedis.hincrByFloat(commonkey, "weight", 1.1) + " gms");

        System.out.println(jedis.hdel(commonkey, "weight-in-gms"));

        System.out.println(jedis.hgetAll(commonkey));
    }

    private static void testList()
    {
        Jedis jedis = getResource();

        System.out.println(jedis.del("mykey4list"));

        String commonkey = "mykey4list";
        String commonkey1 = "mykey4list1";

        for (int index = 0; index < 3; index++)
        {
            jedis.lpush(commonkey, "Message - " + index);
        }

        System.out.println(jedis.lrange(commonkey, 0, -1));

        for (int index = 3; index < 6; index++)
        {
            jedis.rpush(commonkey, "Message - " + index);
        }

        System.out.println(jedis.lrange(commonkey, 0, -1));

        System.out.println(jedis.lindex(commonkey, 0));

        System.out.println(jedis.linsert(commonkey, LIST_POSITION.AFTER, "Message - 5", "Message - 7"));
        System.out.println(jedis.lrange(commonkey, 0, -1));
        System.out.println(jedis.linsert(commonkey, LIST_POSITION.BEFORE, "Message - 7", "Message - 6"));
        System.out.println(jedis.lrange(commonkey, 0, -1));

        System.out.println(jedis.llen(commonkey));

        System.out.println(jedis.lpop(commonkey));

        System.out.println(jedis.lrange(commonkey, 0, -1));

        System.out.println(jedis.lpush(commonkey, "Message - 2", "Message -1.9"));

        System.out.println(jedis.lrange(commonkey, 0, -1));

        System.out.println(jedis.lpushx(commonkey, "Message - 1.8"));

        System.out.println(jedis.lrange(commonkey, 0, -1));

        System.out.println(jedis.lrem(commonkey, 0, "Message - 1.8"));

        System.out.println(jedis.lrange(commonkey, 0, -1));

        System.out.println(jedis.lrem(commonkey, -1, "Message - 7"));

        System.out.println(jedis.lrange(commonkey, 0, -1));

        System.out.println(jedis.lset(commonkey, 7, "Message - 7"));

        System.out.println(jedis.lrange(commonkey, 0, -1));

        System.out.println(jedis.ltrim(commonkey, 2, -4));

        System.out.println(jedis.lrange(commonkey, 0, -1));

        jedis.rpoplpush(commonkey, commonkey1);

        System.out.println(jedis.lrange(commonkey, 0, -1));
        System.out.println(jedis.lrange(commonkey1, 0, -1));
    }

    private static void testSet()
    {
        Jedis jedis = getResource();
        jedis.sadd("follow:cricket", "vinoo.das@junk-mail.com", "vinoo.das1@junk-mail.com", "vinoo.das3@junk-mail.com");
        System.out.println(jedis.smembers("follow:cricket"));
        System.out.println(jedis.scard("follow:cricket"));
        jedis.sadd("follow:redis", "vinoo.das1@junk-mail.com", "vinoo.das2@junk-mail.com");
        System.out.println(jedis.smembers("follow:redis"));
        System.out.println(jedis.scard("follow:redis"));

        // intersect the above sets to give name who is interested in cricket
        // and redis
        System.out.println(jedis.sinter("Cricket:followers", "follow:redis"));

        jedis.sinterstore("follow:redis+cricket", "follow:cricket", "follow:redis");
        System.out.println(jedis.smembers("follow:redis+cricket"));

        System.out.println(jedis.sismember("follow:redis+cricket", "vinoo.das@junk-mail.com"));
        System.out.println(jedis.sismember("follow:redis+cricket", "vinoo.das1@junk-mail.com"));

        jedis.smove("follow:cricket", "follow:redis", "vinoo.das3@junk-mail.com");
        System.out.println(jedis.smembers("follow:redis"));

        System.out.println(jedis.srandmember("follow:cricket"));

        System.out.println(jedis.spop("follow:cricket"));
        System.out.println(jedis.smembers("follow:cricket"));

        jedis.sadd("follow:cricket", "wrong-data@junk-mail.com");
        System.out.println(jedis.smembers("follow:cricket"));
        jedis.srem("follow:cricket", "wrong-data@junk-mail.com");
        System.out.println(jedis.smembers("follow:cricket"));

        System.out.println(jedis.sunion("follow:cricket", "follow:redis"));
        jedis.sunionstore("follow:cricket-or-redis", "follow:cricket", "follow:redis");
        System.out.println(jedis.smembers("follow:cricket-or-redis"));

        System.out.println(jedis.sdiff("follow:cricket", "follow:redis"));
    }

    private static void testSortedSet()
    {
        Jedis jedis = getResource();
        jedis.zadd("purchase", 0, "learning-redis");
        jedis.zadd("purchase", 0, "cassandra");
        jedis.zadd("purchase", 0, "hadoop");

        System.out.println(jedis.zcard("purchase"));

        // purchase a 3 books on redis
        jedis.zincrby("purchase", 1, "learning-redis");
        jedis.zincrby("purchase", 1, "learning-redis");
        jedis.zincrby("purchase", 1, "learning-redis");
        jedis.zincrby("purchase", 1, "learning-redis");

        // purchase a 2 books on cassandra
        jedis.zincrby("purchase", 1, "cassandra");
        jedis.zincrby("purchase", 1, "cassandra");

        // purchase a 1 book on hadoop
        jedis.zincrby("purchase", 1, "hadoop");

        System.out.println(jedis.zcount("purchase", 3, 4));
        System.out.println(jedis.zrange("purchase", 0, 2));
        System.out.println(jedis.zrangeByScore("purchase", 3, 4));

        System.out.println(jedis.zrank("purchase", "learning-redis"));
        System.out.println(jedis.zrank("purchase", "cassandra"));
        System.out.println(jedis.zrank("purchase", "hadoop"));

        System.out.println(jedis.zrevrank("purchase", "learning-redis"));
        System.out.println(jedis.zrevrank("purchase", "cassandra"));
        System.out.println(jedis.zrevrank("purchase", "hadoop"));

        System.out.println(jedis.zscore("purchase", "learning-redis"));
        System.out.println(jedis.zscore("purchase", "cassandra"));
        System.out.println(jedis.zscore("purchase", "hadoop"));

        jedis.zunionstore("purchase:nosql", "purchase");
        System.out.println("-- " + jedis.zrange("purchase:nosql", 0, -1));
        System.out.println("-- " + jedis.zrank("purchase:nosql", "learning-redis"));

        jedis.zrem("purchase:nosql", "hadoop");
        System.out.println("-- " + jedis.zrange("purchase:nosql", 0, -1));
        jedis.zremrangeByRank("purchase:nosql", 0, 1);
        System.out.println("-- " + jedis.zrange("purchase:nosql", 0, -1));
        jedis.zremrangeByScore("purchase:nosql", 3, 4);
        System.out.println("-- " + jedis.zrange("purchase:nosql", 0, -1));

        setResource(jedis);
    }

}
