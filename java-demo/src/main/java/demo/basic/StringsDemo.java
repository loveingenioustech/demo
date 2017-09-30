package demo.basic;

import org.apache.commons.lang3.StringUtils;

public class StringsDemo
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // testSuffix();

        // testSplit("温度适宜,尽情玩耍吧");

        testImmutable();
    }

    private static void testSplit(String input)
    {
        String[] splitArr = StringUtils.split(input, "|AT|");

        for (String s : splitArr)
        {
            System.out.println(s);
        }
    }

    /**
     * 
     */
    private static void testSuffix()
    {
        String filename = "test.xls";
        if (filename.endsWith(".txt"))
        {
            System.out.println("Text file");
        }
        else if (filename.endsWith(".doc"))
        {
            System.out.println("Document file");
        }
        else if (filename.endsWith(".xls"))
        {
            System.out.println("Excel file");
        }
        else if (filename.endsWith(".java"))
        {
            System.out.println("Java source file");
        }
        else
        {
            System.out.println("Other type of file");
        }
    }

    /**
     * 字符串对象（String Object）是非可变的（immutable），这个题目容易迷惑人的地方在s.trim( )这一行。
     */
    private static void testImmutable()
    {
        String s = " Hello ";
        s += " World ";
        s.trim( );
        System.out.println(s);
    }

}
