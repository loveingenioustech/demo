package demo.basic;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 类名称：RecursionDemo
 * 类描述：如何更有效率的递归，缓存，用空间换时间
 * 创建时间：2016年4月28日 上午11:22:51
 * 修改时间：2016年4月28日 上午11:22:51
 * 修改备注：
 */
public class RecursionDemo {
    /**
     * 阶乘 一个正整数的阶乘（英语：factorial）是所有小于及等于该数的正整数的积，并且有0的阶乘为1。
     * n!=1×2×3×...×n。阶乘亦可以递归方式定义：0!=1，n!=(n-1)!×n
     *
     * @Fields FACTORIAL_ARRAY
     */
    private static int[] FACTORIAL_ARRAY = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    private static Map<BigInteger, BigInteger> map = new HashMap<BigInteger, BigInteger>(10000);

    public static void main(String[] args) {
//        demoNormalFactorial();
//
//        demoBetterFactorial();
//
//        demoNormalBigFactorial();
//
//        demoBetterBigFactorial();

//        demoTail();

        demoCycleWithoutFor();
    }

    private static void demoNormalFactorial() {
        long startTime = System.nanoTime();

        for (int i = 0, n = 10; i < n; i++) {
            System.out.println(noramlCalc(i));
        }

        long endTime = System.nanoTime();

        System.out.println(
                "------------------ Elapsed Seconds for testNormalFactorial: " + formatNano(endTime - startTime));
    }

    private static void demoBetterFactorial() {
        long startTime = System.nanoTime();

        for (int i = 0, n = 10; i < n; i++) {
            System.out.println(betterCalc(i));
        }

        long endTime = System.nanoTime();

        System.out.println(
                "------------------ Elapsed Seconds for testBetterFactorial: " + formatNano(endTime - startTime));
    }

    private static void demoNormalBigFactorial() {
        long startTime = System.nanoTime();

        Random random = new Random();

        for (int i = 0, n = 1000; i < n; i++) {
            System.out.println(noramlBigCalc(BigInteger.valueOf(random.nextInt(10000))));
        }

        long endTime = System.nanoTime();

        System.out.println(
                "------------------ Elapsed Seconds for testNormalBigFactorial: " + formatNano(endTime - startTime));
    }

    private static void demoBetterBigFactorial() {
        long startTime = System.nanoTime();

        Random random = new Random();

        for (int i = 0, n = 1000; i < n; i++) {
            System.out.println(betterBigCalc(BigInteger.valueOf(random.nextInt(10000))));
        }

        long endTime = System.nanoTime();

        System.out.println(
                "------------------ Elapsed Seconds for testBetterBigFactorial: " + formatNano(endTime - startTime));
    }

    /**
     * 简单算法，递归
     *
     * @param value
     * @return
     */
    private static int noramlCalc(int value) {
        if (value < 0 || value > 10) {
            throw new IllegalArgumentException();
        }

        if (value == 0) {
            return 1;
        } else {
            return noramlCalc(value - 1) * value;
        }

    }

    /**
     * 改进的算法，直接给出一定数量的阶乘值，避免每次都要计算
     *
     * @param value
     * @return
     */
    private static int betterCalc(int value) {
        return FACTORIAL_ARRAY[value];
    }

    /**
     * 简单粗暴的递归算法
     *
     * @param value
     * @return
     */
    private static BigInteger noramlBigCalc(BigInteger value) {
        if (value.compareTo(BigInteger.valueOf(0)) == 0) {
            return BigInteger.valueOf(1);
        } else {
            BigInteger nPlusOne = value.subtract(BigInteger.valueOf(1));
            return noramlBigCalc(nPlusOne).multiply(value);
        }
    }

    /**
     * 改进的算法，首先尝试从缓存中取，没有再去计算
     *
     * @param value
     * @return
     */
    private static BigInteger betterBigCalc(BigInteger value) {
        if (value.compareTo(BigInteger.valueOf(0)) == 0) {
            return BigInteger.valueOf(1);
        } else {
            // Load value from cache
            BigInteger factor = (BigInteger) map.get(value);

            if (factor != null) {
                return factor;
            }

            BigInteger nPlusOne = value.subtract(BigInteger.valueOf(1));
            factor = betterBigCalc(nPlusOne).multiply(value);

            map.put(value, factor);

            return factor;
        }

    }

    /**
     * 格式化纳秒
     *
     * @param input
     * @return
     */
    private static double formatNano(long input) {
        return (double) input / 1000000000.0;
    }

    private static void demoTail() {
        System.out.println(new TailRecursiveCall().countA("AAA rating"));
    }

    private static void demoCycleWithoutFor(){
        loopLike(1);
    }

    private static int loopLike(int n){
        if (n > 100){
            return 0;
        }
        else{
            System.out.println("Hello World");
        }

        return loopLike(n+1);
    }
}

/**
 * 常规递归方法（亦称，头递归）在上面演示了，这种方式会增加调用栈的大小。每次递归，其入口需要被记录在栈中。
 * 方法返回之前需要给countA(input.substring(1)的结果加一个count。假定count大于1，那么返回结果就是count + countA(input.substring(1))，当然事先要算出来countA(input.substring(1))才行。
 * 同时，这也意味着直到countA(input.substring(1)计算出来才能得到最终的结果。因此，最后需要做的事其实是加法运算，而非递归本身。
 * 尾递归的好处是什么？
 * 在尾递归中，最后要做的是递归，加法运算在之前就已经完成了。一轮递归调用完毕后就没有其他事情了（除了加法运算），因此调用时生成的信息也就没什么用了。
 * 这些无用信息可以丢弃，然后用一组新的参数来调用一次递归方法来产生一个新的结果。这也就是说，栈调用减少带来了内存消耗减少并且程序的性能更好。
 */
class TailRecursiveCall {
    public int countA(String input) {
        // exit condition – recursive calls must have an exit condition
        if (input == null || input.length() == 0) {
            return 0;
        }
        return countA(input, 0);
    }

    public int countA(String input, int count) {
        if (input.length() == 0) {
            return count;
        }
        // check first character of the input
        if (input.substring(0, 1).equals("A")) {
            count = count + 1;
        }

        // recursive call is the last call as the count is cumulative
        return countA(input.substring(1), count);
    }
}

