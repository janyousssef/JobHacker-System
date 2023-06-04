package threads.ticketbooking;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Running test cases...");
        TestCases.ThousandThreads100Seats();
        sleepToAvoidMixedOutput();
        TestCases.SeatPurchasedAlready();
        sleepToAvoidMixedOutput();
        TestCases.EventSoldOut();
    }

    private static void sleepToAvoidMixedOutput() {
        System.out.println("Sleeping for 3 seconds before next test case...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }

    }

}
