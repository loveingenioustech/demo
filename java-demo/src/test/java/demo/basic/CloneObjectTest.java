package demo.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CloneObjectTest
{

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testRaiseSalary() throws CloneNotSupportedException
    {
        CloneObject original = new CloneObject("John Q. Public", 50000);
        original.setHireDay(2000, 1, 1);
        
        CloneObject copy = original.clone();
        copy.raiseSalary(10);
        copy.setHireDay(2002, 12, 31);
        
        System.out.println("original=" + original);
        System.out.println("copy=" + copy);
    }

}
