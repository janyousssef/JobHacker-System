package threads.ticketbooking;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Running test cases...");
        System.out.println("First testcase:\n");
        TestCases.ThousandThreads100Seats();
        System.out.println("Second testcase:\n");
        TestCases.SeatPurchasedAlready();
        System.out.println("Third testcase:\n");
        TestCases.EventSoldOut();
    }

}
