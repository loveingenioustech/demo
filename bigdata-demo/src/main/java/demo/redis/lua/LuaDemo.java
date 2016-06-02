package demo.redis.lua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import demo.EnvConstant;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class LuaDemo
{
    private static JedisPool pool = new JedisPool(EnvConstant.HOST_REDIS, EnvConstant.PORT_REDIS);

    public static void main(String[] args)
    {
        testLuaScript();
    }

    private static void testLuaScript()
    {
        Jedis jedis = pool.getResource();
        jedis.auth(EnvConstant.AUTH_REDIS);
        String luaScript = readScript("LuaScript.txt");

        String result = (String) jedis.eval(luaScript, Arrays.asList("msg"),
                Arrays.asList("Learning Redis", "Now I am learning Lua for Redis", "prepare for the test again"));
        System.out.println(result);
        jedis.close();
    }

    private static String readScript(String filename)
    {
        StringBuffer string = new StringBuffer();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(LuaDemo.class.getResourceAsStream(filename), "UTF-8")))
        {
            String currentline;
            while ((currentline = br.readLine()) != null)
            {
                string.append(currentline);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return string.toString();
    }

}
