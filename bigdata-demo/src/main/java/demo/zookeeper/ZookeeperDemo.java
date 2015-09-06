package demo.zookeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperDemo
{

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws Exception
    {
        // testConnect();

        // testDataWatcher();

        testClusterMonitor();
    }

    private static void testConnect() throws IOException
    {
        String hostPort = "192.168.1.192:2181";
        String zpath = "/";
        List<String> zooChildren = new ArrayList<String>();
        ZooKeeper zk = new ZooKeeper(hostPort, 2000, null);
        if (zk != null)
        {
            try
            {
                zooChildren = zk.getChildren(zpath, false);
                System.out.println("Znodes of '/': ");
                for (String child : zooChildren)
                {
                    // print the children
                    System.out.println(child);
                }
            }
            catch (KeeperException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static void testDataWatcher() throws KeeperException, InterruptedException
    {
        DataWatcher dw = new DataWatcher();
        dw.printData();
        dw.run();
    }

    private static void testClusterMonitor() throws IOException, KeeperException, InterruptedException
    {
        String hostPort = "192.168.1.192:2181";
        new ClusterMonitor(hostPort).run();
    }

}
