package demo.util;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChineseUtilTest
{

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testSortByPinyin()
    {
        String[] arr = { "张三", "李四", "王五", "赵六", "JAVA", "123", "$%$#", "哈哈A", "1哈哈A", "1哈哈b", "1哈哈a", "哈哈", "哈", "怡情" };

        ChineseUtil.sortByPinyin(arr);

        for (String s : arr)
        {
            System.out.println(s);
        }
    }

    @Test
    public void testSplit2List()
    {
        String zhStr = "中国aadf的111萨bbb菲的zz萨菲";

        List<String> zhList = ChineseUtil.split2List(zhStr);

        System.out.println(zhList);
    }

    @Test
    public void testSplit2Array()
    {
        String zhStr = "中国aadf的111萨bbb菲的zz萨菲";

        String[] zhArr = ChineseUtil.split2Array(zhStr);

        ChineseUtil.sortByPinyin(zhArr);

        for (String s : zhArr)
        {
            System.out.println(s);
        }
    }

}
