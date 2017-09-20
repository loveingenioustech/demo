package demo.io;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Employee
{
    private String name;
    private int age;
    private double salary;
    private Date hireDay;

    public static final int len = 8;// String 型的定长，相当于数据库中的类型长度

    public Employee()
    {

    }

    public Employee(@NotNull String name, int age, double salary, int year, int month, int day)
    {
        System.out.println(name + ": " + name.getBytes().length + " : " + name.length());

        // 设置姓名
        if (name.getBytes().length >= len)
        {
            this.name = name.substring(0, len - 1);
        }
        else
        {
            while (name.getBytes().length < len)
            {
                name += "\u0000";// 空白 ，占8b
            }
            this.name = name;
        }

        // 设置年龄
        this.age = age;

        // 设置薪水
        this.salary = salary;

        // 设置入职日期
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        this.hireDay = calendar.getTime();
    }

    /**
     * Writes employee data to a print writer
     * 
     * @param out
     *            the print writer
     */
    public void writeData(@NotNull PrintWriter out) throws IOException
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(hireDay);
        out.println(name + "|" + salary + "|" + calendar.get(Calendar.YEAR) + "|" + (calendar.get(Calendar.MONTH) + 1)
                + "|" + calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Reads employee data from a buffered reader
     * 
     * @param in
     *            the buffered reader
     */
    public void readData(@NotNull BufferedReader in) throws IOException
    {
        String s = in.readLine();
        StringTokenizer t = new StringTokenizer(s, "|");
        name = t.nextToken();
        salary = Double.parseDouble(t.nextToken());
        int y = Integer.parseInt(t.nextToken());
        int m = Integer.parseInt(t.nextToken());
        int d = Integer.parseInt(t.nextToken());
        GregorianCalendar calendar = new GregorianCalendar(y, m - 1, d);
        hireDay = calendar.getTime();
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge()
    {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public Date getHireDay()
    {
        return hireDay;
    }

    public void setHireDay(Date hireDay)
    {
        this.hireDay = hireDay;
    }

    /**
     * @return the len
     */
    public static int getLen()
    {
        return len;
    }

    @NotNull
    @Override
    public String toString()
    {
        return "Employee [name=" + name + ", age=" + age + ", salary=" + salary + ", hireDay=" + hireDay + "]";
    }

}
