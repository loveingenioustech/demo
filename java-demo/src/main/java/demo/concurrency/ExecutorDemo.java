package demo.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017-05-05.
 */
public class ExecutorDemo {
    private static final int NO_OF_THREADS = 3;

    /**
     * main thread. Always there by default.
     **/
    public static void main(String[] args) {
        // demo1();

        demo2();
    }

    public static void demo1() {
        ExecutorService executor = Executors.newFixedThreadPool(NO_OF_THREADS);   // create a pool of 3 threads
        for (int i = 10000; i < 10100; i++) {
            Runnable worker = new Sum(i);               // create worker threads
            executor.execute(worker);                   // add runnables to the work queue
        }
        // This will make the executor accept no new threads
        // and finish all existing threads in the queue
        executor.shutdown();

        // Wait until all threads have completed
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

    public static void demo2() {
        ExecutorService executor = Executors.newFixedThreadPool(NO_OF_THREADS);                       // create a pool of 3 threads
        List<Future<String>> list = new ArrayList<Future<String>>(10);  // provides facility to return results asynchronously
        for (int i = 10000; i < 10100; i++) {
            Callable<String> worker = new Sum2(i);                 // create worker threads
            Future<String> submit = executor.submit(worker);      // add callables to the work queue
            list.add(submit);                                            // provides facility to return results asynchronously
        }

        //process the results asynchronously when each thread completes its task
        for (Future<String> future : list) {
            try {
                System.out.println("Thread " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        System.out.println("Finished all threads");
    }
}


class Sum implements Runnable {
    int maxNumber;

    public Sum(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    /**
     * method where the thread execution will start
     **/
    public void run() {
        int sum = 0;
        for (int i = 0; i < maxNumber; i++) {
            sum += maxNumber;
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " count is " + sum);
    }
}

class Sum2 implements Callable<String> {
    int maxNumber;

    public Sum2(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    /**
     * method where the thread execution will start
     * this can return a value
     */
    public String call() {
        int sum = 0;
        for (int i = 0; i <= maxNumber; i++) {
            sum += maxNumber;
        }
        return Thread.currentThread().getName() + " count is " + sum;
    }
}