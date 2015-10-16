package demo.time;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDemo
{

    public static void main(String[] args) throws ParseException
    {
        formatLong(1444531321651l);

        toLong("2015-10-12 00:00:00");
    }

    private static void formatLong(long time)
    {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss .SSS");
        System.out.println(format.format(date));
    }

    private static void toLong(String string) throws ParseException
    {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = f.parse(string);
        long milliseconds = d.getTime();
        System.out.println(milliseconds);
    }

}
