package demo.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import demo.test.perf.PerfTest;
import demo.test.perf.PerfTestParam;
import demo.test.perf.PerfTester;
import org.jetbrains.annotations.NotNull;

public class SetDemo
{
    @NotNull
    static List<PerfTest<Set<Integer>>> setTests = new ArrayList<PerfTest<Set<Integer>>>();

    static
    {
        setTests.add(new PerfTest<Set<Integer>>("add")
        {
            public int test(@NotNull Set<Integer> set, @NotNull PerfTestParam tp)
            {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++)
                {
                    set.clear();
                    for (int j = 0; j < size; j++)
                        set.add(j);
                }
                return loops * size;
            }
        });
        setTests.add(new PerfTest<Set<Integer>>("contains")
        {
            public int test(@NotNull Set<Integer> set, @NotNull PerfTestParam tp)
            {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        set.contains(j);
                return loops * span;
            }
        });
        setTests.add(new PerfTest<Set<Integer>>("iterate")
        {
            public int test(@NotNull Set<Integer> set, @NotNull PerfTestParam tp)
            {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++)
                {
                    Iterator<Integer> it = set.iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * set.size();
            }
        });
    }

    public static void main(@NotNull String[] args)
    {
        if (args.length > 0)
            PerfTester.defaultParams = PerfTestParam.array(args);
        PerfTester.fieldWidth = 10;
        PerfTester.run(new TreeSet<Integer>(), setTests);
        PerfTester.run(new HashSet<Integer>(), setTests);
        PerfTester.run(new LinkedHashSet<Integer>(), setTests);
    }

}
