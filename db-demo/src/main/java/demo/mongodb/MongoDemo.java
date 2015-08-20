package demo.mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoDemo
{

    private final static int PORT = 27017;
    private final static String HOST = "192.168.1.192";

    public static void main(String args[])
    {
        // testConnect();

        // testSecureConnect();

        // testInsert();

        // testArrayInsert();

        testQuery();
    }

    /**
     * 测试连接
     */
    private static void testConnect()
    {
        try
        {
            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient(HOST, PORT);

            DB db = mongoClient.getDB("test");

            System.out.println("Successfully connected to MongoDB");

        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 用户名/密码连接
     */
    private static void testSecureConnect()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("test");

            System.out.println("Connect to secure database successfully");
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 插入
     */
    private static void testInsert()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("javastuff");
            DBObject doc = new BasicDBObject("name", "owen").append("age", 47).append("email", "owen@mail.com")
                    .append("phone", "111-222-333");

            coll.insert(doc);

        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 插入数组
     */
    private static void testArrayInsert()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("javastuff");

            List<DBObject> kids = new ArrayList<>();
            kids.add(new BasicDBObject("name", "mike"));
            kids.add(new BasicDBObject("name", "faye"));

            DBObject doc = new BasicDBObject("name", "john").append("age", 35).append("kids", kids)
                    .append("info", new BasicDBObject("email", "john@mail.com").append("phone", "876-134-667"));
            coll.insert(doc);

        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 查询
     */
    private static void testQuery()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("javastuff");
            DBCursor cursor = coll.find();
            try
            {
                while (cursor.hasNext())
                {
                    DBObject object = cursor.next();
                    System.out.println(object);
                }
            }
            finally
            {
                cursor.close();
            }

        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
