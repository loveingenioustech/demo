package demo.concurrency;

public class MemoryLeakDemo
{

    public static void main(String[] args)
    {
        demoLocalVariables();
    }

    public static void demoLocalVariables()
    {
        int bigArray[] = new int[100000];
        // Do some computations with bigArray and get a result.
        int result = compute(bigArray);
        
        // We no longer need bigArray. It will get garbage collected when
        // there are no more references to it. Because bigArray is a local
        // variable, it refers to the array until this method returns. But
        // this method doesn't return. So we've got to explicitly get rid
        // of the referenceourselves, so the garbage collector knows it can
        // reclaim the array.
        bigArray = null;
        
        // Loop forever, handling the user's input
        for (;;){
            handle_input(result);
        }
            
    }
    
    private static int compute(int[] bigArray)
    {
        // TODO Auto-generated method stub
        return 0;
    }    

    private static void handle_input(int result)
    {
        // TODO Auto-generated method stub       
    }



}
