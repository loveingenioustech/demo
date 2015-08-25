package demo.basic;

public class MathDemo
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        testAbs();
    }

    private static void testAbs()
    {
        System.out.println(Math.abs(-2));

        // Integer.MIN_VALUE是-2147483648的，但一个32位整数可以包含最高值是+2147483647。
        // 试图代表+2147483648在32位int将有效地“翻转”到-2147483648。这是有符号整数，两个的二进制表示+2147483648和-2147483648是相同的。
        // 这一点，也没有问题，但如+2147483648被认为是超出范围。
        System.out.println(Math.abs(-2147483648));
    }

}
