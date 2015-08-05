package demo.algorithm;

import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.primitives.Ints;

public class SearchsTest
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
    public void testBinarySearchIntIntArray()
    {
        int[] numbers = { 1, 3, 8, 2, 9, 4 };
        System.out.println(Searchs.binarySearch(3, numbers));
        System.out.println(Searchs.binarySearch(33, numbers));
    }

    @Test
    public void testBinarySearchIntegerListOfInteger()
    {
        int[] numbers = { 1, 3, 8, 2, 9, 4 };
        List<Integer> numberList = Ints.asList(numbers);
        Collections.sort(numberList);
        System.out.println(Searchs.binarySearch(3, numberList));
        System.out.println(Searchs.binarySearch(33, numberList));

    }

}
