package threads.ticketbooking;

import threads.ticketbooking.domain.Event;

public class TestCases {
    static void ThousandThreads100Seats() {
        Event event = new Event(10, 10);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> event.bookSeat(Thread.currentThread()
                                                    .getName(), (int) (Math.random() * 100))).start();
        }
        printStats("\n\n\n\n================ ThousandThreads100Seats Statistics =================", event,
                   "=====================================================================");
    }

    static void SeatPurchasedAlready() {
        Event event = new Event(10, 10);
        int seatId = 1;
        new Thread(() -> event.bookSeat(Thread.currentThread()
                                                .getName(), seatId)).start();
        new Thread(() -> event.bookSeat(Thread.currentThread()
                                                .getName(), seatId)).start();
        printStats("\n\n\n\n================= SeatPurchasedAlready Statistics =================", event,
                   "===================================================================");
    }

    static void EventSoldOut() {
        Event event = new Event(10, 10);
        event.setSoldOut(true);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> event.bookSeat(Thread.currentThread()
                                                    .getName(), (int) (Math.random() * 100))).start();
        }
        printStats("\n\n\n\n====================== EventSoldOut Statistics ======================", event,
                   "=====================================================================");
    }

    private static void printStats(String top, Event event, String bottom) {
        waitForTestCaseToFinishExecuting();

        System.out.println(top);
        System.out.println(event.getStatistics());
        System.out.println(bottom);
    }

    private static void waitForTestCaseToFinishExecuting() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
    }
}
