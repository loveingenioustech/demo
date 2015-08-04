package demo.algorithm;

import java.util.LinkedList;
import java.util.List;

public class Sort
{
    /**
     * 冒泡排序
     * 
     * @param numbers
     * @return
     */
    public static int[] bubbleSort(int[] numbers)
    {
        // 是否交换完成
        boolean numbersSwitched;
        do
        {
            // 一开始置为 false 如果所有的都交换完成了，则不会进第二重循环
            numbersSwitched = false;

            for (int i = 0; i < numbers.length - 1; i++)
            {
                if (numbers[i + 1] < numbers[i])
                {
                    int tmp = numbers[i + 1];
                    numbers[i + 1] = numbers[i];
                    numbers[i] = tmp;
                    numbersSwitched = true;
                }
            }
        }
        while (numbersSwitched);

        return numbers;
    }

    /**
     * 插入排序
     * 
     * @param numbers
     * @return
     */
    public static Integer[] insertSort(int[] numbers)
    {
        // 新建一个空白List
        final List<Integer> sortedList = new LinkedList<Integer>();
        originalList: for (Integer number : numbers)
        {
            for (int i = 0; i < sortedList.size(); i++)
            {
                if (number < sortedList.get(i))
                {
                    sortedList.add(i, number);
                    continue originalList;
                }
            }
            sortedList.add(sortedList.size(), number);
        }

        return sortedList.toArray(new Integer[sortedList.size()]);
    }

}
