package demo.algorithm;

import static org.junit.Assert.*;

import org.junit.Test;

public class StopwatchTest
{

    @Test
    public void testElapsedTime()
    {
        // 创建秒表
        Stopwatch stopwatch = new Stopwatch();
        
        // 开始做业务 
        int sum = 0;
        for(int i=0;i<5000000;i++){
            sum += i;
        }
        
        System.out.println("Sum: " + sum);
        
        System.out.println("Elapsed Time: " + stopwatch.elapsedTime());      
    }

}
