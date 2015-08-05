package demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.google.common.primitives.Ints;

public class Sorts
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
    public static int[] insertSort(final int[] numbers)
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

        // return sortedList.toArray(new Integer[sortedList.size()]);
        return Ints.toArray(sortedList);
    }

    /**
     * 归并排序
     * 
     * @param numbers
     * @return
     */
    public static int[] mergeSort(final int[] numbers)
    {
        if (numbers.length < 2)
        {
            return numbers;
        }

        // 一刀切，分两半
        final int[] leftHalf = Arrays.copyOfRange(numbers, 0, numbers.length / 2);
        final int[] rightHalf = Arrays.copyOfRange(numbers, numbers.length / 2, numbers.length);

        return merge(mergeSort(leftHalf), mergeSort(rightHalf));

    }

    /**
     * 执行归并
     * 
     * @param left
     * @param right
     * @return
     */
    private static int[] merge(final int[] left, final int[] right)
    {
        int leftPtr = 0;
        int rightPtr = 0;

        // 初始化
        final List<Integer> merged = new ArrayList<Integer>(left.length + right.length);

        // 主循环
        while (leftPtr < left.length && rightPtr < right.length)
        {
            if (left[leftPtr] < right[rightPtr])
            {
                merged.add(left[leftPtr]);
                leftPtr++;
            }
            else
            {
                merged.add(right[rightPtr]);
                rightPtr++;
            }
        }

        // 补充
        while (leftPtr < left.length)
        {
            merged.add(left[leftPtr]);
            leftPtr++;
        }
        while (rightPtr < right.length)
        {
            merged.add(right[rightPtr]);
            rightPtr++;
        }

        return Ints.toArray(merged);
    }

    /**
     * 自然排序，利用 Arrays
     * 
     * @param numbers
     * @return
     */
    public static int[] naturallySort(final int[] numbers)
    {
        Arrays.sort(numbers);
        return numbers;
    }

    /**
     * 快速排序
     * 
     * @param numbers
     * @return
     */
    public static int[] quickSort(final int[] numbers)
    {
        if (numbers.length < 2)
        {
            return numbers;
        }
        int pivot = numbers[0];

        final List<Integer> lower = new ArrayList<Integer>();
        final List<Integer> higher = new ArrayList<Integer>();
        for (int i = 1; i < numbers.length; i++)
        {
            if (numbers[i] < pivot)
            {
                lower.add(numbers[i]);
            }
            else
            {
                higher.add(numbers[i]);
            }
        }

        int[] sorted = new int[lower.size() + higher.size() + 1];

        int[] sortedLower = quickSort(Ints.toArray(lower));
        System.arraycopy(sortedLower, 0, sorted, 0, sortedLower.length);
        sorted[sortedLower.length] = pivot;

        int[] sortedhigher = quickSort(Ints.toArray(higher));
        System.arraycopy(sortedhigher, 0, sorted, sortedLower.length + 1, sortedhigher.length);

        return sorted;
    }

}
