package demo.collection;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

import demo.test.perf.PerfTest;
import demo.test.perf.PerfTestParam;
import demo.test.perf.PerfTester;
import demo.util.CountingGenerator;
import demo.util.RandomUtils;

public class ListDemo
{
    static Random rand = new Random();
    static int reps = 1000;

    static List<PerfTest<List<Integer>>> tests = new ArrayList<PerfTest<List<Integer>>>();
    static List<PerfTest<LinkedList<Integer>>> qTests = new ArrayList<PerfTest<LinkedList<Integer>>>();

    static
    {
        tests.add(new PerfTest<List<Integer>>("add")
        {
            public int test(List<Integer> list, PerfTestParam tp)
            {
                int loops = tp.loops;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++)
                {
                    list.clear();
                    for (int j = 0; j < listSize; j++)
                    {
                        list.add(j);
                    }

                }
                return loops * listSize;
            }
        });
        tests.add(new PerfTest<List<Integer>>("get")
        {
            public int test(List<Integer> list, PerfTestParam tp)
            {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for (int i = 0; i < loops; i++)
                {
                    // 随机获取
                    list.get(rand.nextInt(listSize));
                }

                return loops;
            }
        });
        tests.add(new PerfTest<List<Integer>>("set")
        {
            public int test(List<Integer> list, PerfTestParam tp)
            {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for (int i = 0; i < loops; i++)
                {
                    list.set(rand.nextInt(listSize), 47);
                }

                return loops;
            }
        });
        tests.add(new PerfTest<List<Integer>>("iteradd")
        {
            public int test(List<Integer> list, PerfTestParam tp)
            {
                final int LOOPS = 1000000;
                int half = list.size() / 2;
                ListIterator<Integer> it = list.listIterator(half);
                for (int i = 0; i < LOOPS; i++)
                {
                    it.add(47);
                }

                return LOOPS;
            }
        });
        tests.add(new PerfTest<List<Integer>>("insert")
        {
            public int test(List<Integer> list, PerfTestParam tp)
            {
                int loops = tp.loops;
                for (int i = 0; i < loops; i++)
                {
                    list.add(5, 47); // Minimize random-access cost
                }

                return loops;
            }
        });
        tests.add(new PerfTest<List<Integer>>("remove")
        {
            public int test(List<Integer> list, PerfTestParam tp)
            {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++)
                {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while (list.size() > 5)
                    {
                        list.remove(5); // Minimize random-access cost
                    }

                }
                return loops * size;
            }
        });
        // Tests for queue behavior:
        qTests.add(new PerfTest<LinkedList<Integer>>("addFirst")
        {
            public int test(LinkedList<Integer> list, PerfTestParam tp)
            {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++)
                {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addFirst(47);
                }
                return loops * size;
            }
        });
        qTests.add(new PerfTest<LinkedList<Integer>>("addLast")
        {
            public int test(LinkedList<Integer> list, PerfTestParam tp)
            {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++)
                {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addLast(47);
                }
                return loops * size;
            }
        });
        qTests.add(new PerfTest<LinkedList<Integer>>("rmFirst")
        {
            public int test(LinkedList<Integer> list, PerfTestParam tp)
            {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++)
                {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while (list.size() > 0)
                        list.removeFirst();
                }
                return loops * size;
            }
        });
        qTests.add(new PerfTest<LinkedList<Integer>>("rmLast")
        {
            public int test(LinkedList<Integer> list, PerfTestParam tp)
            {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++)
                {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while (list.size() > 0)
                        list.removeLast();
                }
                return loops * size;
            }
        });
    }

    public static void main(String[] args)
    {
        demoPerf(args);
    }

    private static void demoPerf(String[] args)
    {
        if (args.length > 0)
        {
            PerfTester.defaultParams = PerfTestParam.array(args);
        }

        // Can only do these two tests on an array:
        PerfTester<List<Integer>> arrayTest = new PerfTester<List<Integer>>(null, tests.subList(1, 3))
        {
            // This will be called before each test.
            // It produces a non-resizeable array-backed list:
            @Override
            protected List<Integer> initialize(int size)
            {

                Integer[] ia = RandomUtils.array(Integer.class, new CountingGenerator.Integer(), size);

                return Arrays.asList(ia);
            }
        };

        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        PerfTester.defaultParams = PerfTestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
        if (args.length > 0)
        {
            PerfTester.defaultParams = PerfTestParam.array(args);
        }

        ListTester.run(new ArrayList<Integer>(), tests);
        ListTester.run(new LinkedList<Integer>(), tests);
        ListTester.run(new Vector<Integer>(), tests);

        PerfTester.fieldWidth = 12;
        PerfTester<LinkedList<Integer>> qTest = new PerfTester<LinkedList<Integer>>(new LinkedList<Integer>(), qTests);
        qTest.setHeadline("Queue tests");
        qTest.timedTest();

    }

    static class ListTester extends PerfTester<List<Integer>>
    {

        public ListTester(List<Integer> container, List<PerfTest<List<Integer>>> tests)
        {
            super(container, tests);
        }

        @Override
        protected List<Integer> initialize(int size)
        {
            container.clear();
            container.addAll(new CountingIntegerList(size));
            return container;
        }

        public static void run(List<Integer> list, List<PerfTest<List<Integer>>> tests)
        {
            new ListTester(list, tests).timedTest();
        }

    }

}

class CountingIntegerList extends AbstractList<Integer>
{
    private int size;

    public CountingIntegerList(int size)
    {
        this.size = size < 0 ? 0 : size;
    }

    @Override
    public Integer get(int index)
    {
        return Integer.valueOf(index);
    }

    @Override
    public int size()
    {
        return size;
    }

}
