package demo.algorithm;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortTest
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
    public void testBubbleSort()
    {
        int[] numbers = { 1, 3, 8, 2, 9, 4 };
        int[] sortNumbers = Sort.bubbleSort(numbers);

        for (int i : sortNumbers)
        {
            System.out.println(i);
        }
    }

    @Test
    public void testInsertSort()
    {
        int[] numbers = { 1, 3, 8, 2, 9, 4 };
        Integer[] sortNumbers = Sort.insertSort(numbers);

        for (Integer i : sortNumbers)
        {
            System.out.println(i);
        }

    }

}
