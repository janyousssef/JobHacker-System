package threads.ticketbooking;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Running test cases...");
        TestCases.ThousandThreads100Seats();
        AvoidMixedOutput();
        TestCases.SeatPurchasedAlready();
        AvoidMixedOutput();
        TestCases.EventSoldOut();
    }

    private static void AvoidMixedOutput() {
        /*
         2 threads because main thread and IntelliJ's thread
         https://stackoverflow.com/questions/30561424/thread-count-is-different-when-debugging-or-running-on-intellij
        */
        final int MIN_THREADS = 2;
        while (Thread.activeCount() > MIN_THREADS) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
