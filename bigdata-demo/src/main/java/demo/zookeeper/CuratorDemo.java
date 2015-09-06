package demo.zookeeper;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

public class CuratorDemo
{
    private final static String CONNECT_STRING = "192.168.1.192:2181";

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        // testCuratorClient();

        testCuratorFramework();
    }

    public static void testCuratorClient() throws Exception
    {
        CuratorZookeeperClient client = new CuratorZookeeperClient(CONNECT_STRING, 10000, 10000, null,
                new RetryOneTime(1));
        client.start();
        try
        {
            client.blockUntilConnectedOrTimedOut();
            String path = client.getZooKeeper().create("/test_znode", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
            System.out.println(path);
        }
        finally
        {
            client.close();
        }
    }

    public static void testCuratorFramework() throws Exception
    {
        CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECT_STRING, new RetryOneTime(1));
        client.start();

        try
        {
            String path = client.create().withMode(CreateMode.PERSISTENT).forPath("/test_curator", "".getBytes());
            System.out.println(path);
        }
        finally
        {
            client.close();
        }
    }

}
