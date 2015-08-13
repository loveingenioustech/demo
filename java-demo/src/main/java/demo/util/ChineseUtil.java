package demo.util;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChineseUtil
{
    public static void sortByPinyin(String[] zhArr)
    {
        Collator cmp = Collator.getInstance(java.util.Locale.CHINA);

        Arrays.sort(zhArr, cmp);
    }

    public static List<String> split2List(String content)
    {
        char[] cArray = content.toCharArray();
        List<String> result = new ArrayList<String>();

        String sTemp = "";
        for (int i = 0; i < cArray.length; i++)
        {
            sTemp = new String(cArray, i, 1);
            result.add(sTemp);
        }

        return result;
    }

    public static String[] split2Array(String content)
    {
        char[] cArray = content.toCharArray();
        String[] sArray = new String[cArray.length];

        String sTemp = "";
        for (int i = 0; i < cArray.length; i++)
        {
            sTemp = new String(cArray, i, 1);
            sArray[i] = sTemp;
        }
        return sArray;
    }

}
