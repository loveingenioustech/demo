package demo.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import demo.EnvConstant;

public class MasterDemo implements Watcher
{
    ZooKeeper zk;

    public static void main(String[] args) throws Exception
    {
        MasterDemo m = new MasterDemo();
        m.startZK();        // wait for a bit
        Thread.sleep(6000);
        m.stopZK();
    }

    void startZK() throws IOException
    {
        zk = new ZooKeeper(EnvConstant.CONN_ZOOKEEPER, 15000, this);
    }

    void stopZK() throws InterruptedException
    {
        zk.close();
    }

    @Override
    public void process(WatchedEvent event)
    {
        System.out.println(event);
    }

}
