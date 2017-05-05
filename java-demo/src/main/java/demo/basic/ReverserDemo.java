package demo.basic;

/**
 * Created by Administrator on 2017-05-05.
 */
public class ReverserDemo {
    public static void main(String args[]) {
        String str = "Robin";

        System.out.println(reverse1(str));
        System.out.println(reverse2(str));
        System.out.println(reverse3(str));
        System.out.println(reverse4(str));
        System.out.println(reverse5(str));
    }

    /**
     * JDK中StringBuffer的反转方法，它不仅速度快，效率高，而且还知道如何处理unicode代理对(surrogate pairs)
     *
     * @param str
     * @return
     */
    public static String reverse1(String str) {
        if ((null == str) || (str.length() <= 1)) {
            return str;
        }
        return new StringBuffer(str).reverse().toString();
    }

    /**
     * 使用递归
     *
     * @param str
     * @return
     */
    public static String reverse2(String str) {
        if ((null == str) || (str.length() <= 1)) {
            return str;
        }
        return reverse2(str.substring(1)) + str.charAt(0);
    }

    /**
     * 在适当的位置调动StringBuffer
     * @param str
     * @return
     */
    public static String reverse3(String str) {
        if ((null == str) || (str.length() <= 1)) {
            return str;
        }

        StringBuffer result = new StringBuffer(str);
        for (int i = 0; i < (str.length() / 2); i++) {
            int swapIndex = str.length() - 1 - i;
            char swap = result.charAt(swapIndex);
            result.setCharAt(swapIndex, result.charAt(i));
            result.setCharAt(i, swap);
        }
        return result.toString();
    }

    /**
     * 调用数组
     *
     * @param str
     * @return
     */
    public static String reverse4(String str) {
        if ((null == str) || (str.length() <= 1)) {
            return str;
        }
        char[] chars = str.toCharArray();
        int right = chars.length - 1;
        for (int left = 0; left < right; left++) {
            char swap = chars[left];
            chars[left] = chars[right];
            chars[right--] = swap;
        }
        return new String(chars);
    }

    /**
     * 使用StringBuffer 的append方法
     *
     * @param str
     * @return
     */
    public static String reverse5(String str) {
        if ((null == str) || (str.length() <= 1)) {
            return str;
        }
        StringBuffer reverse = new StringBuffer(str.length());
        for (int i = str.length() - 1; i >= 0; i--) {
            reverse.append(str.charAt(i));
        }
        return reverse.toString();
    }

}


