package demo.mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
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

        // testQuery();

        // testSimplePojoInsert();

        // testSimplePojoQuery();

        // testGsonQuery();

        // testGsonInsert();

        // testIndex();

        // testTextIndex();

        // testTextLanguageIndex();

        // testBulk();

        testBulkOrderd();
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

    /**
     * 插入自定义对象
     */
    private static void testSimplePojoInsert()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("pojo");
            SimplePojo obj = new SimplePojo();
            obj.put("user", "user1");
            obj.put("message", "message");
            obj.put("date", new Date());

            coll.insert(obj);
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 
     */
    private static void testSimplePojoQuery()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("pojo");
            coll.setObjectClass(SimplePojo.class);
            SimplePojo tw = (SimplePojo) coll.findOne();
            System.out.println(tw.get("user"));
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 
     */
    private static void testGsonQuery()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("javastuff");

            Gson gson = new GsonBuilder().registerTypeAdapter(Customer.class, new CustomAdapter()).create();

            BasicDBObject doc = new BasicDBObject("name", "owen");

            DBObject obj = coll.findOne(doc);

            Customer c = gson.fromJson(obj.toString(), Customer.class);
            System.out.println("Found customer " + c);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 
     */
    private static void testGsonInsert()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("javastuff");

            Gson gson = new Gson();

            BasicDBObject doc = new BasicDBObject("name", "robin").append("info",
                    new BasicDBObject("age", 25).append("email", "robin@gmail.com").append("phone", "321-456-778"));

            coll.insert(doc);

            DBObject obj = coll.findOne(doc);

            CustomerInfo c = gson.fromJson(obj.toString(), CustomerInfo.class);

            System.out.println("Found customer " + c);

        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 索引
     */
    private static void testIndex()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("indextest");

            coll.createIndex(new BasicDBObject("userid", 1));
            for (int ii = 0; ii < 10000; ii++)
            {

                BasicDBObject doc = new BasicDBObject("userid", ii);

                coll.insert(doc);

            }
            BasicDBObject doc = new BasicDBObject("userid", "1111");
            DBObject explainObject = coll.find(doc).explain();

            System.out.println(explainObject);
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 
     */
    private static void testTextIndex()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("textitems");

            coll.createIndex(new BasicDBObject("content", "text"));

            coll.insert(new BasicDBObject().append("content", "mytext other content"));

            BasicDBObject search = new BasicDBObject("$search", "mytext");

            BasicDBObject textSearch = new BasicDBObject("$text", search);

            int count = coll.find(textSearch).count();
            System.out.println("Found text search matches: " + count);

        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 
     */
    private static void testTextLanguageIndex()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("textitems");

            coll.createIndex(new BasicDBObject("content", "text"));

            coll.insert(new BasicDBObject("_id", 0).append("textcontent", "Some data"));
            coll.insert(new BasicDBObject("_id", 1).append("textcontent", "Other data"));
            coll.insert(new BasicDBObject("_id", 2).append("textcontent", "Not important"));

            BasicDBObject search = new BasicDBObject("$search", "data");

            DBObject textSearch = new BasicDBObject("$text", search.append("$language", "english"));
            int matchCount = coll.find(textSearch).count();
            System.out.println("Text search matches: " + matchCount);
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 
     */
    private static void testBulk()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("javastuff");

            BulkWriteOperation builder = coll.initializeUnorderedBulkOperation();

            builder.insert(new BasicDBObject("item", "A1"));
            builder.insert(new BasicDBObject("item", "A2"));
            builder.insert(new BasicDBObject("item", "A3"));

            builder.find(new BasicDBObject("item", "A1")).updateOne(
                    new BasicDBObject("$set", new BasicDBObject("A1", "AX")));

            BulkWriteResult result = builder.execute();

            System.out.println("Bulk Completed: Inserted documents " + result.getInsertedCount());
            System.out.println("Bulk Completed: Modified documents " + result.getModifiedCount());

        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 
     */
    private static void testBulkOrderd()
    {
        try
        {
            MongoCredential credential = MongoCredential.createCredential("admin", "admin", "admin2015".toCharArray());

            MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("javastuff");

            BulkWriteOperation builder = coll.initializeOrderedBulkOperation();

            builder.insert(new BasicDBObject("item", "A1"));
            builder.insert(new BasicDBObject("item", "A2"));
            builder.insert(new BasicDBObject("item", "A3"));

            builder.find(new BasicDBObject("item", "A1")).updateOne(
                    new BasicDBObject("$set", new BasicDBObject("A1", "AX")));

            BulkWriteResult result = builder.execute();

            System.out.println("Ordered Bulk Completed: Inserted documents " + result.getInsertedCount());
            System.out.println("Ordered Bulk Completed: Modified documents " + result.getModifiedCount());

        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
