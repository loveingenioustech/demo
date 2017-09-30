package demo.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;

import demo.test.perf.PerfTest;
import demo.test.perf.PerfTestParam;
import demo.test.perf.PerfTester;
import org.jetbrains.annotations.NotNull;

public class MapDemo
{
    @NotNull
    static List<PerfTest<Map<Integer, Integer>>> mapTests = new ArrayList<PerfTest<Map<Integer, Integer>>>();

    // 静态初始化
    static
    {
        mapTests.add(new PerfTest<Map<Integer, Integer>>("put")
        {
            public int test(@NotNull Map<Integer, Integer> map, @NotNull PerfTestParam tp)
            {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++)
                {
                    map.clear();
                    for (int j = 0; j < size; j++)
                    {
                        map.put(j, j);
                    }
                }
                return loops * size;
            }
        });
        mapTests.add(new PerfTest<Map<Integer, Integer>>("get")
        {
            public int test(@NotNull Map<Integer, Integer> map, @NotNull PerfTestParam tp)
            {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                {
                    for (int j = 0; j < span; j++)
                    {
                        map.get(j);
                    }
                }

                return loops * span;
            }
        });
        mapTests.add(new PerfTest<Map<Integer, Integer>>("iterate")
        {
            public int test(@NotNull Map<Integer, Integer> map, @NotNull PerfTestParam tp)
            {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++)
                {
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext())
                    {
                        it.next();
                    }

                }
                return loops * map.size();
            }
        });
    }

    public static void main(@NotNull String[] args)
    {
        // demoNull();

        demoPerf(args);
    }

    private static void demoNull()
    {   // allow duplicate
        // allow null as key
        Map m = new HashMap();
        m.put(null, "Test");
        m.put(null, "Fest");
        System.out.println(m);
    }

    private static void demoPerf(@NotNull String[] args)
    {
        if (args.length > 0)
            PerfTester.defaultParams = PerfTestParam.array(args);

        // Map
        PerfTester.run(new TreeMap<Integer, Integer>(), mapTests);
        PerfTester.run(new HashMap<Integer, Integer>(), mapTests);
        PerfTester.run(new LinkedHashMap<Integer, Integer>(), mapTests);
        PerfTester.run(new IdentityHashMap<Integer, Integer>(), mapTests);
        PerfTester.run(new WeakHashMap<Integer, Integer>(), mapTests);
        PerfTester.run(new Hashtable<Integer, Integer>(), mapTests);
    }
}
