package demo.collection;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class SortDemo
{

    public static void main(String[] args)
    {
        demoSortedMap();

        demoSortedSet();
    }

    private static void demoSortedMap()
    {
        TreeMap<Integer, String> sortedMap = new TreeMap<Integer, String>(new CountingMapData(10));

        System.out.println(sortedMap);

        Integer low = sortedMap.firstKey();
        Integer high = sortedMap.lastKey();

        System.out.println(low);
        System.out.println(high);

        Iterator<Integer> it = sortedMap.keySet().iterator();
        for (int i = 0; i <= 6; i++)
        {
            if (i == 3)
                low = it.next();
            if (i == 6)
                high = it.next();
            else
                it.next();
        }

        System.out.println(low);
        System.out.println(high);

        System.out.println(sortedMap.subMap(low, high));
        System.out.println(sortedMap.headMap(high));
        System.out.println(sortedMap.tailMap(low));
    }

    private static void demoSortedSet()
    {
        SortedSet<String> sortedSet = new TreeSet<String>();
        Collections.addAll(sortedSet, "one two three four five six seven eight".split(" "));

        System.out.println(sortedSet);

        String low = sortedSet.first();
        String high = sortedSet.last();

        System.out.println(low);
        System.out.println(high);

        Iterator<String> it = sortedSet.iterator();

        for (int i = 0; i <= 6; i++)
        {
            if (i == 3)
                low = it.next();
            if (i == 6)
                high = it.next();
            else
                it.next();
        }

        System.out.println(low);
        System.out.println(high);
        System.out.println(sortedSet.subSet(low, high));
        System.out.println(sortedSet.headSet(high));
        System.out.println(sortedSet.tailSet(low));
    }

}
