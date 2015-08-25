package demo.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.GregorianCalendar;

public class CloneObject implements Cloneable
{
    private String name;
    private double salary;
    private Date hireDay;

    public CloneObject(String name, double salary)
    {
        super();
        this.name = name;
        this.salary = salary;
    }

    public CloneObject clone() throws CloneNotSupportedException
    {
        // call Object.clone()
        CloneObject cloned = (CloneObject) super.clone();

        // clone mutable fields
        cloned.hireDay = (Date) hireDay.clone();

        return cloned;
    }

    /*// 另一种克隆的算法，先序列化，然后再反序列化 
    public Object clone()
    {
        try
        {
            // save the object to a byte array
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(this);
            out.close();

            // read a clone of the object from the byte array
            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bin);
            Object ret = in.readObject();
            in.close();

            return ret;
        }
        catch (Exception e)
        {
            return null;
        }
    }
     */
    
    public void raiseSalary(double byPercent)
    {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public void setHireDay(int year, int month, int day)
    {
        hireDay = new GregorianCalendar(year, month - 1, day).getTime();
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
     * @return the salary
     */
    public double getSalary()
    {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    /**
     * @return the hireDay
     */
    public Date getHireDay()
    {
        return hireDay;
    }

    /**
     * @param hireDay the hireDay to set
     */
    public void setHireDay(Date hireDay)
    {
        this.hireDay = hireDay;
    }

    /*
     * (non-Javadoc)
     * <p>Title: toString</p>
     * <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "CloneObject [name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
    }

}
