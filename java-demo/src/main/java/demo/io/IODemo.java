package demo.io;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IODemo
{

    public static void main(String[] args)
    {
        demoWrite();

        demoRead();
    }

    private static void demoWrite()
    {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 30, 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 28, 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 16, 40000, 1990, 3, 15);

        try
        {
            // save all employee records to the file employee.dat
            PrintWriter out = new PrintWriter(new FileWriter("employee.dat"));
            writeData(staff, out);
            out.close();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }

    }

    private static void demoRead()
    {
        try
        {
            // retrieve all records into a new array
            BufferedReader in = new BufferedReader(new FileReader("employee.dat"));
            Employee[] newStaff = readData(in);
            in.close();

            // print the newly read employee records
            for (Employee e : newStaff)
            {
                System.out.println(e);
            }

        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * Writes all employees in an array to a print writer
     * 
     * @param employees
     *            an array of employees
     * @param out
     *            a print writer
     */
    static void writeData(@NotNull Employee[] employees, @NotNull PrintWriter out) throws IOException
    {
        // write number of employees
        out.println(employees.length);

        for (Employee e : employees)
            e.writeData(out);
    }

    /**
     * Reads an array of employees from a buffered reader
     * 
     * @param in
     *            the buffered reader
     * @return the array of employees
     */
    @NotNull
    static Employee[] readData(@NotNull BufferedReader in) throws IOException
    {
        // retrieve the array size
        int n = Integer.parseInt(in.readLine());

        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++)
        {
            employees[i] = new Employee();
            employees[i].readData(in);
        }

        return employees;
    }
}
