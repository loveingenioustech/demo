package demo.io;

public class Employee
{
    private String name;
    private int age;
    public static final int len = 8;// String 型的定长，相当于数据库中的类型长度

    public Employee(String name, int age)
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

    /**
     * @return the len
     */
    public static int getLen()
    {
        return len;
    }

}
