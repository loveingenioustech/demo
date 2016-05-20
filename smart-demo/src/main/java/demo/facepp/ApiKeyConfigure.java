package demo.facepp;

public class ApiKeyConfigure
{
    private static String key = "0b56613032e07bc8ca87bc39024fefae";

    private static String secret = "ZV4EaT5ZR72rN8JJcHggPFt3nd6ubagw";

    private static String name = "face-demo";

    public static String getKey()
    {
        return key;
    }

    public static void setKey(String key)
    {
        ApiKeyConfigure.key = key;
    }

    public static String getSecret()
    {
        return secret;
    }

    public static void setSecret(String secret)
    {
        ApiKeyConfigure.secret = secret;
    }

    public static String getName()
    {
        return name;
    }

    public static void setName(String name)
    {
        ApiKeyConfigure.name = name;
    }
}
