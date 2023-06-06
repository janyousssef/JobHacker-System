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
        // 2 Because main thread and IntelliJ's thread are running.
        while (Thread.activeCount() > 2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
