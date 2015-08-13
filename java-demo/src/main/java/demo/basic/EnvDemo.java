package demo.basic;

import java.util.Map;

public class EnvDemo
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        showProperties();

        showVariables();
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

}
