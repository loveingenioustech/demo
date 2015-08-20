package demo.time;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OneTimerTaskTest
{

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testRun()
    {
        TimerTask timerTask = new OneTimerTask();

        // running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);

        System.out.println("TimerTask started");
        
        // cancel after sometime
        try
        {
            Thread.sleep(120000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        // 取消任务
        timer.cancel();
        System.out.println("TimerTask cancelled");
        try
        {
            Thread.sleep(30000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
