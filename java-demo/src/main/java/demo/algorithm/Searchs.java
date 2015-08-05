package demo.algorithm;

import java.util.Arrays;
import java.util.List;

public class Searchs
{

    /**
     * 二分查找法
     * 
     * @param key
     * @param numbers
     * @return
     */
    public static int binarySearch(int key, int[] numbers)
    {
        // precondition: array numbers[] is sorted
        int[] sortedNumbers = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(sortedNumbers);

        int lo = 0;
        int hi = sortedNumbers.length - 1;
        while (lo <= hi)
        {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < sortedNumbers[mid])
                hi = mid - 1;
            else if (key > sortedNumbers[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }


    /**
     * 只管存不存在，不返回具体位置
     * 
     * @param key
     * @param numbers
     * @return
     */
    public static boolean binarySearch(final Integer key, final List<Integer> numbers)
    {
        if (numbers == null || numbers.isEmpty())
        {
            return false;
        }

        final Integer comparison = numbers.get(numbers.size() / 2);

        if (key.equals(comparison))
        {
            return true;
        }

        if (key < comparison)
        {
            return binarySearch(key, numbers.subList(0, numbers.size() / 2));
        }
        else
        {
            return binarySearch(key, numbers.subList(numbers.size() / 2 + 1, numbers.size()));
        }
    }

}
