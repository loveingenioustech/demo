package demo.collection;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayDemo
{

    public static void main(String[] args)
    {
        demoByte();

        demoInt();

        demoCopy();

        demoSort();

        demoPair();

        demoGrow();
    }

    /**
     * Gets the minimum and maximum of an array of strings.
     * 
     * @param a
     *            an array of strings
     * @return a pair with the min and max value, or null if a is null or empty
     */
    public static Pair<String> minmax(String[] a)
    {
        if (a == null || a.length == 0)
            return null;
        String min = a[0];
        String max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if (min.compareTo(a[i]) > 0)
                min = a[i];
            if (max.compareTo(a[i]) < 0)
                max = a[i];
        }
        return new Pair<String>(min, max);
    }

    /**
     * Gets the minimum and maximum of an array of objects of type T.
     * 
     * @param a
     *            an array of objects of type T
     * @return a pair with the min and max value, or null if a is null or empty
     */
    public static <T extends Comparable> Pair<T> minmax(T[] a)
    {
        if (a == null || a.length == 0)
            return null;
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if (min.compareTo(a[i]) > 0)
                min = a[i];
            if (max.compareTo(a[i]) < 0)
                max = a[i];
        }
        return new Pair<T>(min, max);
    }

    /**
     * should be false
     */
    private static void demoByte()
    {
        byte[] a = { 1, 2, 3 };
        byte[] b = (byte[]) a.clone();
        System.out.print(a == b);
    }

    /**
     * 
     */
    private static void demoInt()
    {
        int[] myArray = new int[5];
        System.out.println(myArray[0]);
    }

    private static void demoCopy()
    {
        int[] i = new int[7];
        int[] j = new int[10];
        Arrays.fill(i, 47);
        Arrays.fill(j, 99);

        System.out.println("i = " + Arrays.toString(i));
        System.out.println("j = " + Arrays.toString(j));

        System.arraycopy(i, 0, j, 0, i.length);

        System.out.println("j = " + Arrays.toString(j));
    }

    private static void demoGrow()
    {
        int[] a = { 1, 2, 3 };
        a = (int[]) goodArrayGrow(a);
        System.out.println(Arrays.toString(a));

        String[] b = { "Tom", "Dick", "Harry" };
        b = (String[]) goodArrayGrow(b);
        System.out.println(Arrays.toString(b));

        System.out.println("The following call will generate an exception.");
        b = (String[]) badArrayGrow(b);
    }

    /**
     * This method attempts to grow an array by allocating a new array and
     * copying all elements.
     * Exception in thread "main" java.lang.ClassCastException:
     * [Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
     * 
     * @param a
     *            the array to grow
     * @return a larger array that contains all elements of a. However, the
     *         returned array has type Object[], not the same type as a
     */
    static Object[] badArrayGrow(Object[] a)
    {
        int newLength = a.length * 11 / 10 + 10;
        // 用这种方式扩大数组，不行，会报类型转换错误
        Object[] newArray = new Object[newLength];
        System.arraycopy(a, 0, newArray, 0, a.length);
        return newArray;
    }

    /**
     * This method grows an array by allocating a new array of the same type and
     * copying all elements.
     * 
     * @param a
     *            the array to grow. This can be an object array or a
     *            fundamental type array
     * @return a larger array that contains all elements of a.
     */
    static Object goodArrayGrow(Object a)
    {
        Class<?> cl = a.getClass();
        if (!cl.isArray())
            return null;
        Class<?> componentType = cl.getComponentType();
        int length = Array.getLength(a);
        int newLength = length * 11 / 10 + 10;

        // 通过反射，创建指定类型的数组
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, length);
        return newArray;
    }

    private static void demoSort()
    {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Harry Hacker", 35000);
        staff[1] = new Employee("Carl Cracker", 75000);
        staff[2] = new Employee("Tony Tester", 38000);

        Arrays.sort(staff);

        // print out information about all Employee objects
        for (Employee e : staff)
        {
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());
        }

    }

    private static void demoPair()
    {
        String[] words = { "Mary", "had", "a", "little", "lamb" };
        Pair<String> mm = minmax(words);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }

}

class Employee implements Comparable<Employee>
{
    public Employee(String n, double s)
    {
        name = n;
        salary = s;
    }

    public String getName()
    {
        return name;
    }

    public double getSalary()
    {
        return salary;
    }

    public void raiseSalary(double byPercent)
    {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    /**
     * Compares employees by salary
     * 
     * @param other
     *            another Employee object
     * @return a negative value if this employee has a lower salary than
     *         otherObject, 0 if the salaries are the same, a positive value
     *         otherwise
     */
    public int compareTo(Employee other)
    {
        if (salary < other.salary)
            return -1;
        if (salary > other.salary)
            return 1;
        return 0;
    }

    private String name;
    private double salary;
}
