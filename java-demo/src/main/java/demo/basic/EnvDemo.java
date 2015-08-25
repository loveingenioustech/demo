package demo.basic;

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
        // showProperties();

        // showVariables();

        testPreferences();
    }

    /**
     * 
     */
    private static void showVariables()
    {
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

}
