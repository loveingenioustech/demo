package demo.collection;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Administrator on 2017-04-25.
 */
public class DistinctSort {
    public static void main(String[] args){
        List origin = new ArrayList();
        origin.add("A");
        origin.add("D");
        origin.add("F");
        origin.add("A");
        origin.add("H");
        origin.add("B");

        printList(origin);

        List handled = RemoveDuplicateAndSort(origin);
        printList(handled);
    }

    /**
     * 通过把原有列表传入一个LinkedHashSet来去除重复的元素。
     * 在这个情况里，LinkedHashSet可以保持元素原来的顺序。
     * 如果这个顺序是不需要的话，那么上面的LinkedHashSet可以用HashSet来替换。
     *
     * @param list
     * @param <e>
     * @return
     */
    @NotNull
    public static <e> List<e> RemoveDuplicateAndSort (@NotNull List<e> list) {
        return new ArrayList<e>(new LinkedHashSet<e>(list));
    }

    private static void printList(@NotNull List list){
        for (Object o: list){
            System.out.print(o + " ");
        }
        System.out.println();
    }

}
