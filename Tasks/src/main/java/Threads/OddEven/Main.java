package Threads.OddEven;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {
        int max = 10;
        Thread oddThread = new Thread(new NumberPrinter(1, max, "Odd Thread"));
        Thread evenThread = new Thread(new NumberPrinter(2, max, "Even Thread"));
        for (int i = 0; i < max; i++) {
            executor.execute(oddThread);
            executor.execute(evenThread);
        }
        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdown();

    }
}
