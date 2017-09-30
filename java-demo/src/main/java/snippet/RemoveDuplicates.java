package snippet;

import java.util.*;

/**
 * Created by Administrator on 2017/10/1.
 */
public class RemoveDuplicates {
    public static void main(String args[]){
        byHashSet();

        byLinkedHashSet();
    }

    public static void byHashSet(){
        //ArrayList with duplicates String
        List<String> duplicateList = (List<String>) Arrays.asList("Android" , "Android", "iOS", "Windows mobile");
        System.out.println("size of Arraylist with duplicates: " + duplicateList.size()); //should print 4 becaues of duplicates Android

        System.out.println(duplicateList);

        //Converting ArrayList to HashSet to remove duplicates
        HashSet<String> listToSet = new HashSet<String>(duplicateList);

        //Creating Arraylist without duplicate values
        List<String> listWithoutDuplicates = new ArrayList<String>(listToSet);
        System.out.println("size of ArrayList without duplicates: " + listToSet.size()); //should print 3 becaues of duplicates Android removed

        System.out.println(listWithoutDuplicates);
    }

    /**
     * keep the insertion order of elements
     */
    public static void byLinkedHashSet(){
        //ArrayList with duplicates String
        List<String> duplicateList = (List<String>) Arrays.asList("Android" , "Android", "iOS", "Windows mobile");
        //should print 4 becaues of duplicates Android
        System.out.println("size of Arraylist with duplicates: " + duplicateList.size());
        System.out.println("ArrayList with duplicates: " + duplicateList);

        //Converting ArrayList to HashSet to remove duplicates
        LinkedHashSet<String> listToSet = new LinkedHashSet<String>(duplicateList);

        //Creating Arraylist without duplicate values
        List<String> listWithoutDuplicates = new ArrayList<String>(listToSet);
        //should print 3 becaues of duplicates Android removed
        System.out.println("size of ArrayList without duplicates: " + listToSet.size());
        System.out.println("ArrayList after removing duplicates in same order: " + listWithoutDuplicates);
    }
}

