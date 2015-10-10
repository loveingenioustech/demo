package demo.basic;

public class ArrayDemo
{

    public static void main(String[] args)
    {
        demoByte();

        demoInt();
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
        int[] myArray= new int[5];
        System.out.println(myArray[0]);        
    }
}
