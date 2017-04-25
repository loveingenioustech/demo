package demo.collection;

import java.util.Arrays;

/**
 * Created by Administrator on 2017-04-25.
 */
public class InsertingElementsToArray {
    public static void main(String args[]) {
        insertUnsortedArray("B");

        insertSortedArray("B");
    }

    /**
     * 插入元素到一个无序的数组里
     *
     * @param toInsert
     */
    public static void insertUnsortedArray(String toInsert) {
        String[] unsortedArray = {"A", "D", "C"};
        String[] newUnsortedArray = new String[unsortedArray.length + 1];
        System.arraycopy(unsortedArray, 0, newUnsortedArray, 0, 3);
        newUnsortedArray[newUnsortedArray.length - 1] = toInsert;
        System.out.println(Arrays.toString(newUnsortedArray));
    }

    /**
     * 插入元素到一个有序数组
     *
     * @param toInsert
     */
    public static void insertSortedArray(String toInsert) {
        String[] sortedArray = {"A", "C", "D"};
        /*
        * Binary search returns the index of the search item
        * if found, otherwise returns the minus insertion point. This example
        * returns index = -2, which means the elemnt is not found and needs to
        * be inserted as a second element.
        */
        int index = Arrays.binarySearch(sortedArray, toInsert);
        if (index < 0) {
            // array indices are zero based. -2 index means insertion point of
            // -(-2)-1 = 1,  so insertIndex = 1
            int insertIndex = -index - 1;
            String[] newSortedArray = new String[sortedArray.length + 1];
            System.arraycopy(sortedArray, 0, newSortedArray, 0, insertIndex);
            System.arraycopy(sortedArray, insertIndex,
                    newSortedArray, insertIndex + 1, sortedArray.length - insertIndex);
            newSortedArray[insertIndex] = toInsert;
            System.out.println(Arrays.toString(newSortedArray));
        }
    }

}
