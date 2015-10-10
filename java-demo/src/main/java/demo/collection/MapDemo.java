package demo.collection;

import java.util.HashMap;
import java.util.Map;

public class MapDemo
{

    public static void main(String[] args)
    {
        demoNull();

    }

    private static void demoNull()
    {
        Map m = new HashMap();
        m.put(null, "Test");
        m.put(null, "Fest");
        System.out.println(m);
    }

}
