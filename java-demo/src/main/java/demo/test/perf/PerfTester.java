package demo.test.perf;

import java.util.List;

public class PerfTester<C>
{
    public static int fieldWidth = 8;
    public static PerfTestParam[] defaultParams = PerfTestParam.array(10, 5000, 100, 5000, 1000, 5000, 10000, 500);

    protected C container;
    private String headline = "";
    private List<PerfTest<C>> tests;

    private static int sizeWidth = 5;
    private static String sizeField = "%" + sizeWidth + "s";
    private PerfTestParam[] paramList = defaultParams;

    public PerfTester(C container, List<PerfTest<C>> tests)
    {
        this.container = container;
        this.tests = tests;
        if (container != null)
        {
            headline = container.getClass().getSimpleName();
        }
    }

    public PerfTester(C container, List<PerfTest<C>> tests, PerfTestParam[] paramList)
    {
        this(container, tests);
        this.paramList = paramList;
    }

    /**
     * Override this to modify pre-test initialization
     * 
     * @param size
     * @return
     */
    protected C initialize(int size)
    {
        return container;
    }

    private static String stringField()
    {
        return "%" + fieldWidth + "s";
    }

    private static String numberField()
    {
        return "%" + fieldWidth + "d";
    }

    public void setHeadline(String newHeadline)
    {
        headline = newHeadline;
    }

    // Generic methods for convenience :
    public static <C> void run(C cntnr, List<PerfTest<C>> tests)
    {
        new PerfTester<C>(cntnr, tests).timedTest();
    }

    public static <C> void run(C cntnr, List<PerfTest<C>> tests, PerfTestParam[] paramList)
    {
        new PerfTester<C>(cntnr, tests, paramList).timedTest();
    }

    /**
     * Calculate width and pad with '-'
     */
    private void displayHeader()
    {
        int width = fieldWidth * tests.size() + sizeWidth;
        int dashLength = width - headline.length() - 1;

        StringBuilder head = new StringBuilder(width);
        for (int i = 0; i < dashLength / 2; i++)
        {
            head.append('-');
        }

        head.append(' ');
        head.append(headline);
        head.append(' ');

        for (int i = 0; i < dashLength / 2; i++)
        {
            head.append('-');
        }

        System.out.println(head);

        // Print column headers:
        System.out.format(sizeField, "size");
        for (PerfTest test : tests)
        {
            System.out.format(stringField(), test.name);
        }

        System.out.println();
    }

    /**
     * Run the tests for this container
     */
    public void timedTest()
    {
        displayHeader();
        for (PerfTestParam param : paramList)
        {
            System.out.format(sizeField, param.size);
            for (PerfTest<C> test : tests)
            {
                C kontainer = initialize(param.size);

                long start = System.nanoTime();
                // Call the overriden method:
                int reps = test.test(kontainer, param);
                long duration = System.nanoTime() - start;
                long timePerRep = duration / reps; // Nanoseconds
                System.out.format(numberField(), timePerRep);
            }

            System.out.println();
        }
    }

}
