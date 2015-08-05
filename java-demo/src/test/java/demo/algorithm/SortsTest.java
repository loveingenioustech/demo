package demo.algorithm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortsTest
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
        int[] sortNumbers = Sorts.bubbleSort(numbers);

        printNumbers(sortNumbers);
    }

    @Test
    public void testInsertSort()
    {
        int[] numbers = { 1, 3, 8, 2, 9, 4 };
        int[] sortNumbers = Sorts.insertSort(numbers);

        printNumbers(sortNumbers);
    }

    @Test
    public void testMergeSort()
    {
        int[] numbers = { 1, 3, 8, 2, 9, 4 };
        int[] sortNumbers = Sorts.mergeSort(numbers);

        printNumbers(sortNumbers);
    }

    @Test
    public void testNaturallySort()
    {
        int[] numbers = { 1, 3, 8, 2, 9, 4 };
        int[] sortNumbers = Sorts.naturallySort(numbers);

        printNumbers(sortNumbers);
    }

    @Test
    public void testQuickSort()
    {
        int[] numbers = { 1, 3, 8, 2, 9, 4 };
        int[] sortNumbers = Sorts.quickSort(numbers);

        printNumbers(sortNumbers);
    }

    /**
     * @param sortNumbers
     */
    private void printNumbers(int[] sortNumbers)
    {
        for (int i : sortNumbers)
        {
            System.out.println(i);
        }
    }

}
