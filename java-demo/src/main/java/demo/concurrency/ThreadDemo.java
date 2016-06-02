package demo.concurrency;

public class ThreadDemo
{

    public static void main(String[] args)
    {
        demoUncaughtExceptionHandler();
    }

    public static void demoUncaughtExceptionHandler()
    {
        // This thread just throws an exception
        Thread handledThread = new Thread(() -> {
            throw new UnsupportedOperationException();
        });
        
        // Giving threads a name helps with debugging
        handledThread.setName("My Broken Thread");
        
        // Here's a handler for the error.
        handledThread.setUncaughtExceptionHandler((t, e) -> {
            System.err.printf("Exception in thread %d '%s':" + "%s at line %d of %s%n", t.getId(),  // Thread
                                                                                                    // id
                    t.getName(),  // Thread name
                    e.toString(), // Exception name and message
                    e.getStackTrace()[0].getLineNumber(), e.getStackTrace()[0].getFileName());
        });

        handledThread.start();
    }

}
