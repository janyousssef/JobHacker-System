package Threads.OddEven;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    /*
    A solution to this problem can be found here:
    https://www.javatpoint.com/multitasking-in-multithreading#:~:text=Printing%20even%20and%20odd%20numbers%20using%20two%20threads
    But I found that solution too verbose and not very elegant.
    I decided to use a thread pool instead.

     */
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        int max = 10;
        Thread oddThread = new Thread(new NumberPrinter(1, max, "Odd Thread"));
        Thread evenThread = new Thread(new NumberPrinter(2, max, "Even Thread"));
        for (int i = 0; i < max; i++) {
            executor.execute(oddThread);
            //Wait for 100 nanoseconds to make sure that the odd thread is executed first
            Thread.sleep(0,100);
            executor.execute(evenThread);
        }
        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdown();

    }
}
