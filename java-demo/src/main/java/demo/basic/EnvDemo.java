package demo.basic;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class EnvDemo
{

    /**
     * 系统属性在和Preferences API都是键值对，前者只能当前应用程序中共享数据，而后者可以在用户的各个应用或用户之间共享数据。
     * 
     * @param args
     * @throws BackingStoreException
     */
    public static void main(String[] args) throws BackingStoreException
    {
        showProperties();

        showVariables();

        showCpuInfo();

        showMemoryInfo();

        showRootFileInfo();

        // testPreferences();
    }

    /**
     * 
     */
    private static void showVariables()
    {
        System.out.println("=========================================");

        Map<String, String> vars = System.getenv();

        for (String var : vars.keySet())
        {
            System.out.println(var + " = " + vars.get(var));
        }
    }

    private static void showProperties()
    {
        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));
    }

    private static void testPreferences() throws BackingStoreException
    {
        Preferences prefs = Preferences.userNodeForPackage(EnvDemo.class);

        prefs.put("Location", "Oz");
        prefs.put("Footwear", "Ruby Slippers");
        prefs.putInt("Companions", 4);
        prefs.putBoolean("Are there witches?", true);

        int usageCount = prefs.getInt("UsageCount", 0);
        usageCount++;
        prefs.putInt("UsageCount", usageCount);

        for (String key : prefs.keys())
        {
            System.out.println(key + ": " + prefs.get(key, null));
        }

        // You must always provide a default value:
        System.out.println("How many companions does Dorothy have? " + prefs.getInt("Companions", 0));
    }

    private static void showCpuInfo()
    {
        System.out.println("=========================================");

        int availableProcessors = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
        long lastSystemTime = System.nanoTime();
        long lastProcessCpuTime = 0;

        if (ManagementFactory.getOperatingSystemMXBean() instanceof OperatingSystemMXBean)
        {
            lastProcessCpuTime = ((com.sun.management.OperatingSystemMXBean) ManagementFactory
                    .getOperatingSystemMXBean()).getProcessCpuTime();
        }

        long systemTime = System.nanoTime();
        long processCpuTime = 0;

        if (ManagementFactory.getOperatingSystemMXBean() instanceof OperatingSystemMXBean)
        {
            processCpuTime = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean())
                    .getProcessCpuTime();
        }

        double cpuUsage = (double) (processCpuTime - lastProcessCpuTime) / (systemTime - lastSystemTime);

        lastSystemTime = systemTime;
        lastProcessCpuTime = processCpuTime;

        System.out.println("cpuUsage / availableProcessors: " + cpuUsage / availableProcessors);
    }

    private static void showMemoryInfo()
    {
        System.out.println("=========================================");

        /* Total number of processors or cores available to the JVM */
        System.out.println("Available processors (cores): " + Runtime.getRuntime().availableProcessors());

        /* Total amount of free memory available to the JVM */
        System.out.println("Free memory (bytes): " + Runtime.getRuntime().freeMemory());

        /* This will return Long.MAX_VALUE if there is no preset limit */
        long maxMemory = Runtime.getRuntime().maxMemory();
        /* Maximum amount of memory the JVM will attempt to use */
        System.out.println("Maximum memory (bytes): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

        /* Total memory currently in use by the JVM */
        System.out.println("Total memory (bytes): " + Runtime.getRuntime().totalMemory());

    }

    private static void showRootFileInfo()
    {
        System.out.println("=========================================");

        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();

        /* For each filesystem root, print some info */
        for (File root : roots)
        {
            System.out.println("File system root: " + root.getAbsolutePath());
            System.out.println("Total space (bytes): " + root.getTotalSpace());
            System.out.println("Free space (bytes): " + root.getFreeSpace());
            System.out.println("Usable space (bytes): " + root.getUsableSpace());
        }

    }

}
