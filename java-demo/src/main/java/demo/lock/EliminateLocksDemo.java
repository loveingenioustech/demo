package demo.lock;

/**
 * Created by Administrator on 2017-08-07.
 */
public class EliminateLocksDemo {
    public static void main(String args[]) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 2000000; i++) {
            createStringBuffer("JVM", "Diagnosis");
        }
        long bufferCost = System.currentTimeMillis() - start;
        System.out.println("createStringBuffer: " + bufferCost + " ms");
    }

    public static String createStringBuffer(String s1, String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }

    // 启用锁消除
    // -server -XX:+DoEscapeAnalysis -XX:+EliminateLocks
    // 125ms

    // 禁用锁消除
    //-server -XX:+DoEscapeAnalysis -XX:-EliminateLocks
    // 221ms

}
