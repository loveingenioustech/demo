/**
 * @Title: OneTimerTask.java
 * @Package demo.time
 * @date 2015-8-20 下午3:11:28
 * @version
 */
package demo.time;

import java.util.Date;
import java.util.TimerTask;

public class OneTimerTask extends TimerTask
{

    /*
     * (non-Javadoc)
     * <p>Title: run</p>
     * <p>Description: </p>
     * @see java.util.TimerTask#run()
     */
    @Override
    public void run()
    {
        System.out.println("Timer task started at:" + new Date());

        completeTask();

        System.out.println("Timer task finished at:" + new Date());
    }

    /**
     * 完成任务
     */
    private void completeTask()
    {
        try
        {
            // assuming it takes 20 secs to complete the task
            Thread.sleep(20000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

}
