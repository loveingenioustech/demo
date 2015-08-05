package demo.util;

import java.util.AbstractList;
import java.util.List;

public class CommonUtils
{
    public static List<Integer> asList(final int[] numbers)
    {
        return new AbstractList<Integer>()
        {
            public Integer get(int i)
            {
                return numbers[i];
            }

            public int size()
            {
                return numbers.length;
            }
        };
    }
        

}
