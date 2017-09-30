package demo.util;

import org.jetbrains.annotations.NotNull;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays; 

public class ChineseUtil
{
    public static void sortByPinyin(@NotNull String[] zhArr)
    {
        Collator cmp = Collator.getInstance(java.util.Locale.CHINA);

        Arrays.sort(zhArr, cmp);
    }

    @NotNull
    public static List<String> split2List(@NotNull String content)
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

    @NotNull
    public static String[] split2Array(@NotNull String content)
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
