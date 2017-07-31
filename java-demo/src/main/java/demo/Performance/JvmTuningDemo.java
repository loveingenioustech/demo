package demo.Performance;

/**
 * Created by Administrator on 2017-07-31.
 */
public class JvmTuningDemo {
    public static void main(String[] args) {
        byte[] b=null;
        for(int i=0;i<10;i++)
            b=new byte[1*1024*1024];
    }

    // 第一组参数 -Xmx20m -Xms20m -Xmn1m  -XX:+PrintGCDetails
    /*
    [GC (Allocation Failure) [PSYoungGen: 512K->472K(1024K)] 512K->472K(19968K), 0.0205913 secs] [Times: user=0.02 sys=0.00, real=0.03 secs]
            [GC (Allocation Failure) [PSYoungGen: 984K->504K(1024K)] 984K->640K(19968K), 0.0115108 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
    Heap
    PSYoungGen      total 1024K, used 682K [0x00000000ffe80000, 0x0000000100000000, 0x0000000100000000)
    eden space 512K, 34% used [0x00000000ffe80000,0x00000000ffeaca20,0x00000000fff00000)
    from space 512K, 98% used [0x00000000fff80000,0x00000000ffffe010,0x0000000100000000)
    to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
    ParOldGen       total 18944K, used 10376K [0x00000000fec00000, 0x00000000ffe80000, 0x00000000ffe80000)
    object space 18944K, 54% used [0x00000000fec00000,0x00000000ff6220a0,0x00000000ffe80000)
    Metaspace       used 2915K, capacity 4494K, committed 4864K, reserved 1056768K
    class space    used 318K, capacity 386K, committed 512K, reserved 1048576K
    Java HotSpot(TM) 64-Bit Server VM warning: NewSize (1536k) is greater than the MaxNewSize (1024k). A new max generation size of 1536k will be used.
   */

    // 第二组参数 -Xmx20m -Xms20m -Xmn15m  -XX:+PrintGCDetails
    /*
    Heap
     PSYoungGen      total 13824K, used 12288K [0x00000000ff100000, 0x0000000100000000, 0x0000000100000000)
      eden space 12288K, 100% used [0x00000000ff100000,0x00000000ffd00000,0x00000000ffd00000)
      from space 1536K, 0% used [0x00000000ffe80000,0x00000000ffe80000,0x0000000100000000)
      to   space 1536K, 0% used [0x00000000ffd00000,0x00000000ffd00000,0x00000000ffe80000)
     ParOldGen       total 5120K, used 0K [0x00000000fec00000, 0x00000000ff100000, 0x00000000ff100000)
      object space 5120K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff100000)
     Metaspace       used 3109K, capacity 4494K, committed 4864K, reserved 1056768K
      class space    used 337K, capacity 386K, committed 512K, reserved 1048576K
    */

    // 第三组参数 -Xmx20m -Xms20m -Xmn7m  -XX:+PrintGCDetails
    /*[GC (Allocation Failure) [PSYoungGen: 5521K->504K(6656K)] 5521K->1736K(19968K), 0.0131988 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
            [GC (Allocation Failure) [PSYoungGen: 5742K->504K(6656K)] 6974K->2760K(19968K), 0.0028509 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
    PSYoungGen      total 6656K, used 1712K [0x00000000ff900000, 0x0000000100000000, 0x0000000100000000)
    eden space 6144K, 19% used [0x00000000ff900000,0x00000000ffa2e2d8,0x00000000fff00000)
    from space 512K, 98% used [0x00000000fff80000,0x00000000ffffe010,0x0000000100000000)
    to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
    ParOldGen       total 13312K, used 2256K [0x00000000fec00000, 0x00000000ff900000, 0x00000000ff900000)
    object space 13312K, 16% used [0x00000000fec00000,0x00000000fee34020,0x00000000ff900000)
    Metaspace       used 2991K, capacity 4494K, committed 4864K, reserved 1056768K
    class space    used 325K, capacity 386K, committed 512K, reserved 1048576K
    */

    // 第四组参数 -Xmx20m -Xms20m -Xmn7m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
    /*
    [GC (Allocation Failure) [PSYoungGen: 3355K->712K(5632K)] 3355K->1744K(18944K), 0.2606362 secs] [Times: user=0.36 sys=0.02, real=0.26 secs]
            [GC (Allocation Failure) [PSYoungGen: 3863K->1512K(5632K)] 4895K->2720K(18944K), 0.0019816 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
            [GC (Allocation Failure) [PSYoungGen: 4736K->1528K(5632K)] 5944K->2784K(18944K), 0.0200516 secs] [Times: user=0.03 sys=0.00, real=0.02 secs]
    Heap
    PSYoungGen      total 5632K, used 3731K [0x00000000ff900000, 0x0000000100000000, 0x0000000100000000)
    eden space 4096K, 53% used [0x00000000ff900000,0x00000000ffb26f60,0x00000000ffd00000)
    from space 1536K, 99% used [0x00000000ffd00000,0x00000000ffe7e020,0x00000000ffe80000)
    to   space 1536K, 0% used [0x00000000ffe80000,0x00000000ffe80000,0x0000000100000000)
    ParOldGen       total 13312K, used 1256K [0x00000000fec00000, 0x00000000ff900000, 0x00000000ff900000)
    object space 13312K, 9% used [0x00000000fec00000,0x00000000fed3a040,0x00000000ff900000)
    Metaspace       used 2991K, capacity 4494K, committed 4864K, reserved 1056768K
    class space    used 325K, capacity 386K, committed 512K, reserved 1048576K
    */

    // 第五组参数 -Xmx20m -Xms20m -XX:NewRatio=1 -XX:SurvivorRatio=2 -XX:+PrintGCDetails
    /*
    [GC (Allocation Failure) [PSYoungGen: 4626K->1736K(7680K)] 4626K->1744K(17920K), 0.0041164 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    [GC (Allocation Failure) [PSYoungGen: 5981K->1752K(7680K)] 5989K->1760K(17920K), 0.0055788 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
     PSYoungGen      total 7680K, used 4998K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
      eden space 5120K, 63% used [0x00000000ff600000,0x00000000ff92bac0,0x00000000ffb00000)
      from space 2560K, 68% used [0x00000000ffd80000,0x00000000fff36020,0x0000000100000000)
      to   space 2560K, 0% used [0x00000000ffb00000,0x00000000ffb00000,0x00000000ffd80000)
     ParOldGen       total 10240K, used 8K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
      object space 10240K, 0% used [0x00000000fec00000,0x00000000fec02000,0x00000000ff600000)
     Metaspace       used 3036K, capacity 4494K, committed 4864K, reserved 1056768K
      class space    used 330K, capacity 386K, committed 512K, reserved 1048576K
     */


    // 第六组参数 -Xmx20m -Xms20m -XX:NewRatio=1 -XX:SurvivorRatio=3 -XX:+PrintGCDetails
    /*
    [GC (Allocation Failure) [PSYoungGen: 5750K->1736K(8192K)] 5750K->1744K(18432K), 0.0122537 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
    [GC (Allocation Failure) [PSYoungGen: 7035K->1704K(8192K)] 7043K->1712K(18432K), 0.0131848 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
    Heap
    PSYoungGen      total 8192K, used 2851K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
    eden space 6144K, 19% used [0x00000000ff600000,0x00000000ff728ce0,0x00000000ffc00000)
    from space 2048K, 83% used [0x00000000ffe00000,0x00000000fffaa030,0x0000000100000000)
    to   space 2048K, 0% used [0x00000000ffc00000,0x00000000ffc00000,0x00000000ffe00000)
    ParOldGen       total 10240K, used 8K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
    object space 10240K, 0% used [0x00000000fec00000,0x00000000fec02000,0x00000000ff600000)
    Metaspace       used 3002K, capacity 4494K, committed 4864K, reserved 1056768K
    class space    used 325K, capacity 386K, committed 512K, reserved 1048576K
    */


}
